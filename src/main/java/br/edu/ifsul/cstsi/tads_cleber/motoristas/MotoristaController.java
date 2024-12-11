package br.edu.ifsul.cstsi.tads_cleber.motoristas;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("api/motoristas")
public class MotoristaController {
    private final MotoristaRepository motoristaRepository;

    public MotoristaController(MotoristaRepository motoristaRepository) {
        this.motoristaRepository = motoristaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Motorista>> findAll(){
        return ResponseEntity.ok(motoristaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motorista> findById(@PathVariable("id") Long id){
        var motorist = motoristaRepository.findById(id);
        return motorist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Motorista> findByNome(@PathVariable("nome") String nome){
        var name = motoristaRepository.findByNome(nome);
        return name.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(name.get(0));
    }

    @PostMapping
    public ResponseEntity<Motorista> insert(@RequestBody Motorista motorista, UriComponentsBuilder uriBuilder){
        var m = motoristaRepository.save(new Motorista(
                null,
                motorista.getNome(),
                motorista.getEmail(),
                motorista.getTelefone(),
                motorista.getCorridas(),
                motorista.getVeiculo()
        ));
        var location = uriBuilder.path("/api/motoristas/{id}").build(m.getId());
        return ResponseEntity.created(location).body(m);
    }

    @PutMapping("{id}")
    public ResponseEntity<Motorista> update(@PathVariable("id") Long id, @RequestBody Motorista motorista){
        var motoristFind = motoristaRepository.findById(id);
        if(motoristFind.isPresent()){
            var motoristAtt = motoristaRepository.save(new Motorista(
                    id,
                    motorista.getNome(),
                    motorista.getEmail(),
                    motorista.getTelefone(),
                    motorista.getCorridas(),
                    motorista.getVeiculo()
            ));
            return ResponseEntity.ok(motoristAtt);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Motorista> delete(@PathVariable("id") Long id, @RequestBody Motorista motorista){
        var motoristFind = motoristaRepository.findById(id);
        if(motoristFind.isPresent()){
            motoristaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
