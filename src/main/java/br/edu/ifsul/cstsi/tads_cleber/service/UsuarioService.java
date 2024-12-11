package br.edu.ifsul.cstsi.tads_cleber.service;

import br.edu.ifsul.cstsi.tads_cleber.entity.Usuario;
import br.edu.ifsul.cstsi.tads_cleber.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository rep;

    //Listagem Usuario
    public List<Usuario> getUsuario(){
        return rep.findAll();
    }

    //Selecionar pelo ID
    public Usuario getUsuarioById(Long id){
        Optional<Usuario> optional = rep.findById(id);
        return optional.orElse(null);
    }

    //Selecionar pelo Nome
    public List<Usuario> getUsuarioByNome(String nome){
        return new ArrayList<>(rep.findByNome(nome + "%"));
    }

    //Inserir Usuario
    public Usuario insert(Usuario usuario){
        Assert.isNull(usuario.getId(), "Não foi possivel registrar o Usuáro");
        return rep.save(usuario);
    }

    //Atualizar Usuário
    public Usuario update(Usuario usuario){
        Assert.notNull(usuario.getId(), "Não foi possivel atualizar o usuário");

        Optional<Usuario> optional = rep.findById(usuario.getId());
        if (optional.isPresent()){
            Usuario db = optional.get();
            db.setNome(usuario.getNome());
            db.setEmail(usuario.getEmail());
            db.setTelefone(usuario.getTelefone());
            db.setCorridas(usuario.getCorridas());

            rep.save(db);
            return db;
        }else{
            return null;
        }
    }

    //Deletar Usuario
    public void delete(Long id){
            rep.deleteById(id);
        }
}
