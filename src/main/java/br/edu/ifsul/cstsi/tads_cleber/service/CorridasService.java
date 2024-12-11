package br.edu.ifsul.cstsi.tads_cleber.service;

import br.edu.ifsul.cstsi.tads_cleber.entity.Corrida;
import br.edu.ifsul.cstsi.tads_cleber.repository.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class CorridasService {

    @Autowired
    private CorridaRepository corridaRepository;

    public List<Corrida> getCorridas() {return corridaRepository.findAll();}

    public Corrida getCorrida(Long id) {
        Optional<Corrida> optional = corridaRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public Corrida insert(Corrida corrida) {
        Assert.isNull(corrida.getId(), "Não foi possivel registrar a corrida");
        return corridaRepository.save(corrida);
    }

}
