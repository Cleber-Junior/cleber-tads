package br.edu.ifsul.cstsi.tads_cleber.controller;

import br.edu.ifsul.cstsi.tads_cleber.entity.Motorista;
import br.edu.ifsul.cstsi.tads_cleber.entity.Veiculo;

import java.io.Serializable;

public record MotoristaDto(String email, String nome, String telefone, Veiculo veiculo) implements Serializable {
    public MotoristaDto(Motorista motorista){
        this(motorista.getEmail(), motorista.getNome(), motorista.getTelefone(), motorista.getVeiculo());
    }

}
