package br.edu.ifsul.cstsi.tads_cleber.autentication;

import br.edu.ifsul.cstsi.tads_cleber.entity.Usuario;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface AutenticacaoRepository extends Repository<Usuario, Long> {
    Usuario findByEmail(String email);
}
