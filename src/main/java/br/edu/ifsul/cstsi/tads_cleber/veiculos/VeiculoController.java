package br.edu.ifsul.cstsi.tads_cleber.veiculos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Locale;

public class VeiculoController {
    private final VeiculoRepository veiculoRepository;

    public VeiculoController(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> findAll(){
        return ResponseEntity.ok(veiculoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> findById(@PathVariable Long id){
        var veiculo = veiculoRepository.findById(id);
        return veiculo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{veiculo_tipo}")
    public ResponseEntity<Veiculo> findByTipo(@PathVariable String tipoV){
        var tipo = veiculoRepository.findVeiculoByTipo(tipoV);
        return tipo.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(tipo.get(0));
    }

    @PostMapping
    public ResponseEntity<Veiculo> insert(@RequestBody Veiculo veiculo, UriComponentsBuilder uriBuilder){
        var veiculoInsert = veiculoRepository.save(new Veiculo(
                null,
                veiculo.getTipo(),
                veiculo.getPlaca(),
                veiculo.getAnoFabricacao()
        ));
        var location = uriBuilder.path("/api/veiculos/{id}").build(veiculoInsert.getId());
        return ResponseEntity.created(location).body(veiculoInsert);
    }

    @PutMapping("{id}")
    public ResponseEntity<Veiculo> update(@PathVariable("id") Long id, @RequestBody Veiculo veiculo){
        var veiculoFind = veiculoRepository.findById(id);
        if(veiculoFind.isPresent()){
            var veiculoAtt = veiculoRepository.save(new Veiculo(
                    id,
                    veiculo.getPlaca(),
                    veiculo.getTipo(),
                    veiculo.getAnoFabricacao()
            ));
            return ResponseEntity.ok(veiculoAtt);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Veiculo> delete(@PathVariable("id") Long id){
        var veiculoDelete = veiculoRepository.findById(id);
        if(veiculoDelete.isPresent()){
            veiculoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
