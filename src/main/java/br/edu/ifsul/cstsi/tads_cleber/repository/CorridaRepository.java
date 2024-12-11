package br.edu.ifsul.cstsi.tads_cleber.repository;

import br.edu.ifsul.cstsi.tads_cleber.entity.Corrida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorridaRepository extends JpaRepository<Corrida, Long> {
}