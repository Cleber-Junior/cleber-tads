package br.edu.ifsul.cstsi.tads_cleber.motoristas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

    @Query(value = "Select m from Motoristas m where m.nome like ?1")
    List<Motorista> findByNome(String nome);

}
