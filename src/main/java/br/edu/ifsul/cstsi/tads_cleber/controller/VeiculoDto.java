package br.edu.ifsul.cstsi.tads_cleber.controller;

import br.edu.ifsul.cstsi.tads_cleber.entity.Veiculo;

import java.io.Serializable;
import java.time.LocalDate;

public record VeiculoDto(String tipo_veiculo, String placa_veiculo, LocalDate anoFabricacao) implements Serializable {
    public VeiculoDto(Veiculo veiculo){
        this(veiculo.getTipo(), veiculo.getPlaca(), veiculo.getAnoFabricacao());
    }
}
