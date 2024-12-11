package br.edu.ifsul.cstsi.tads_cleber.repository;

import br.edu.ifsul.cstsi.tads_cleber.controller.VeiculoDto;
import br.edu.ifsul.cstsi.tads_cleber.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    @Query(value = "Select v from Veiculos v where v.tipo like ?1")
    List<VeiculoDto> findVeiculoByTipo(String tipo);
}