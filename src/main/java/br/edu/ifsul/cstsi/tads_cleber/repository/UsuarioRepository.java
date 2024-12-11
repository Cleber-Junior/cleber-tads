package br.edu.ifsul.cstsi.tads_cleber.repository;

import br.edu.ifsul.cstsi.tads_cleber.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    @Query(value = "Select u from Usuarios u where u.nome like ?1")
    List<Usuario> findByNome(String nome);

}
