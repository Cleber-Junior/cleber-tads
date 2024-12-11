package br.edu.ifsul.cstsi.tads_cleber.veiculos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    @Query(value = "Select v from Veiculos v where v.tipo like ?1")
    List<Veiculo> findVeiculoByTipo(String tipo);
}