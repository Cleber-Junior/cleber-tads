package br.edu.ifsul.cstsi.tads_cleber.repository;

import br.edu.ifsul.cstsi.tads_cleber.controller.MotoristaDto;
import br.edu.ifsul.cstsi.tads_cleber.entity.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

    @Query(value = "Select m from Motoristas m where m.nome like ?1")
    List<MotoristaDto> findByNome(String nome);

}
