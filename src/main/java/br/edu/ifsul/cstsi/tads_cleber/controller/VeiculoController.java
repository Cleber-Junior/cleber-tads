package br.edu.ifsul.cstsi.tads_cleber.controller;

import br.edu.ifsul.cstsi.tads_cleber.entity.Veiculo;
import br.edu.ifsul.cstsi.tads_cleber.repository.VeiculoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {
    private final VeiculoRepository veiculoRepository;

    public VeiculoController(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    @GetMapping
    public ResponseEntity<List<VeiculoDto>> findAll(){
        return ResponseEntity.ok(veiculoRepository.findAll().stream().map(VeiculoDto::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDto> findById(@PathVariable Long id){
        var veiculo = veiculoRepository.findById(id);
        if(veiculo.isPresent()){
            return ResponseEntity.ok(new VeiculoDto(veiculo.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/tipo/{veiculo_tipo}")
    public ResponseEntity<VeiculoDto> findByTipo(@PathVariable("veiculo_tipo") String veiculo_tipo){
        var tipo = veiculoRepository.findVeiculoByTipo(veiculo_tipo);
        return tipo.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(tipo.get(0));
    }

    @PostMapping
    public ResponseEntity<VeiculoDto> insert(@RequestBody VeiculoDto veiculoDto, UriComponentsBuilder uriBuilder){
        var veiculo = veiculoRepository.save(new Veiculo(
                null,
                veiculoDto.tipo_veiculo(),
                veiculoDto.placa_veiculo(),
                veiculoDto.anoFabricacao()
        ));
        var location = uriBuilder.path("/{id}").buildAndExpand(veiculo.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<VeiculoDto> update(@PathVariable("id") Long id, @RequestBody VeiculoDto veiculoDto){
        var optional = veiculoRepository.findById(id);
        if(optional.isPresent()){
            var veiculo = veiculoRepository.save(new Veiculo(
                    id,
                    veiculoDto.placa_veiculo(),
                    veiculoDto.tipo_veiculo(),
                    veiculoDto.anoFabricacao()
            ));
            return ResponseEntity.ok(new VeiculoDto(veiculo));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<VeiculoDto> delete(@PathVariable("id") Long id){
        var veiculoDelete = veiculoRepository.findById(id);
        if(veiculoDelete.isPresent()){
            veiculoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
