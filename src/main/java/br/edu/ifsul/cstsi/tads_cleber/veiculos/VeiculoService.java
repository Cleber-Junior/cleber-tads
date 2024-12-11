package br.edu.ifsul.cstsi.tads_cleber.veiculos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository repository;

    public List<Veiculo> findAll() {
        return repository.findAll();
    }

    public Veiculo findById(Long id) {
        Optional<Veiculo> optional = repository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public List<Veiculo> findByTipo(String tipo) {
        return new ArrayList<>(repository.findVeiculoByTipo(tipo));
    }

    public Veiculo insert(Veiculo veiculo) {
        Assert.isNull(veiculo.getId(), "Não foi possivel inserir uma veiculo");
        return repository.save(veiculo);
    }

    public Veiculo update(Veiculo veiculo) {
        Assert.notNull(veiculo.getId(), "Não foi possivel atualizar veiculo");

        Optional<Veiculo> optional = repository.findById(veiculo.getId());
        if(optional.isPresent()) {
            Veiculo veiculoAtualizado = optional.get();
            veiculoAtualizado.setAnoFabricacao(veiculo.getAnoFabricacao());
            veiculoAtualizado.setTipo(veiculo.getTipo());
            veiculoAtualizado.setPlaca(veiculo.getPlaca());

            return repository.save(veiculoAtualizado);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
