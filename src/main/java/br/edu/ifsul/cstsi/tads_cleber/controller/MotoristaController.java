package br.edu.ifsul.cstsi.tads_cleber.controller;

import br.edu.ifsul.cstsi.tads_cleber.entity.Motorista;
import br.edu.ifsul.cstsi.tads_cleber.repository.MotoristaRepository;
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
    public ResponseEntity<List<MotoristaDto>> findAll(){
        return ResponseEntity.ok(motoristaRepository.findAll().stream().map(MotoristaDto::new).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<MotoristaDto> findById(@PathVariable("id") Long id){
        var motorist = motoristaRepository.findById(id);
        if(motorist.isPresent()){
            return ResponseEntity.ok(new MotoristaDto(motorist.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{nome}")
    public ResponseEntity<MotoristaDto> findByNome(@PathVariable("nome") String nome){
        var name = motoristaRepository.findByNome(nome);
        return name.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(name.get(0));
    }

    @PostMapping
    public ResponseEntity<MotoristaDto> insert(@RequestBody MotoristaDto motoristaDto, UriComponentsBuilder uriBuilder){
        var motorista = motoristaRepository.save(new Motorista(
                null,
                motoristaDto.nome(),
                motoristaDto.email(),
                motoristaDto.telefone(),
                null,
                motoristaDto.veiculo()
        ));
        var location = uriBuilder.path("{id}").buildAndExpand(motorista.getId()).toUri();
        return ResponseEntity.created(location).build();
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
