package br.edu.ifsul.cstsi.tads_cleber.repository;

import br.edu.ifsul.cstsi.tads_cleber.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Perfil findByNome(String nome);
}
