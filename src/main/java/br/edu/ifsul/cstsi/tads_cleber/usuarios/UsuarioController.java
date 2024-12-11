package br.edu.ifsul.cstsi.tads_cleber.usuarios;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Scanner;
@Controller
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> findById(@PathVariable("id") Long id) {
        var option = usuarioRepository.findById(id);
        return option.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{name})")
    public ResponseEntity<Usuario> findByNome(@PathVariable("nome") String nome) {
        var names = usuarioRepository.findByNome(nome);
        return names.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(names.get(0));
    }

    @PostMapping
    public ResponseEntity<Usuario> insert(@RequestBody Usuario usuario, UriComponentsBuilder uriBuilder) {
        var u = usuarioRepository.save(new Usuario(
                null,
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                null
        ));
        var location = uriBuilder.path("/api/usuarios/{id}").buildAndExpand(u.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        var userFind = usuarioRepository.findById(id);
        if(userFind.isPresent()){
            var userAtt = usuarioRepository.save(new Usuario(
                    id,
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getTelefone(),
                    null
            ));
            return ResponseEntity.ok(userAtt);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> delete(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        var userFind = usuarioRepository.findById(id);
        if(userFind.isPresent()){
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
