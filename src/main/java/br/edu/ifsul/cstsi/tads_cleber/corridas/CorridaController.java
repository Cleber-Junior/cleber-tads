package br.edu.ifsul.cstsi.tads_cleber.corridas;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
@Controller
@RestController
@RequestMapping("api/corridas")
public class CorridaController {

    private final CorridaRepository corridaRepository;

    public CorridaController(CorridaRepository corridaRepository) {
        this.corridaRepository = corridaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Corrida>> findAll() {
        return ResponseEntity.ok(corridaRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Corrida> findById(@PathVariable("id") Long id) {
        var option = corridaRepository.findById(id);
        return option.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{usuario_id}")
    public ResponseEntity<Corrida> findByUserId(@PathVariable("usuario_id") Long usuario_id) {
        var user = corridaRepository.findById(usuario_id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{driver_id}")
    public ResponseEntity<Corrida> findByDriverId(@PathVariable("driver_id") Long driver_id) {
        var driver = corridaRepository.findById(driver_id);
        return driver.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Corrida> inser(@RequestBody Corrida corrida, UriComponentsBuilder uriBuilder) {
        var ride = corridaRepository.save(new Corrida(
                null,
                corrida.getTipoPagamento(),
                corrida.getDetalhePagamento(),
                corrida.getDataInicio(),
                null,
                null,
                null
        ));
        var location = uriBuilder.path("/corridas/{id}").buildAndExpand(ride.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id, @RequestBody Corrida corrida) {
        var driveFind = corridaRepository.findById(id);
        if(driveFind.isPresent()) {
            corridaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
