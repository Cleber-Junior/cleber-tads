package br.edu.ifsul.cstsi.tads_cleber.motoristas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MotoristaService {
    @Autowired
    private MotoristaRepository rep;

    //Listagem
    public List<Motorista> getMotorista(){
        return rep.findAll();
    }


    //Selecionar pelo ID
    public Motorista getMotoristaById(Long id){
        Optional<Motorista> optional = rep.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    //Selecionar pelo Nome
    public List<Motorista> getMotoristaByNome(String nome){
        return new ArrayList<>(rep.findByNome(nome + "%"));
    }

    //Inserir Motorista
    public Motorista insert(Motorista motorista) {
        Assert.isNull(motorista.getId(),"Não foi possível inserir o registro");
        return rep.save(motorista);
    }

    //Atualizar motorista
    public Motorista update(Motorista motorista){
        Assert.notNull(motorista.getId(), "Não foi possivel atualizar o registro");

        Optional<Motorista> optional = rep.findById(motorista.getId());
        if(optional.isPresent()){
            Motorista db = optional.get();
            db.setNome(motorista.getNome());
            db.setEmail(motorista.getEmail());
            db.setVeiculo(motorista.getVeiculo());
            db.setTelefone(motorista.getTelefone());
            db.setCorridas(motorista.getCorridas());

            rep.save(db);

            return db;
        }else{
            return null;
        }
    }

    //Deletar motorista
    public void delete(Long id){
        rep.deleteById(id);
    }

}
