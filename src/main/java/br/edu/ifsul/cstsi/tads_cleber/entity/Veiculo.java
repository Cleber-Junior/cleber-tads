package br.edu.ifsul.cstsi.tads_cleber.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Veiculos")
@Table(name = "Veiculo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Veiculo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "veiculo_tipo", nullable = false, length =50)
    private String tipo;
    @Column(name = "veiculo_placa", nullable = false, length = 7)
    private String placa;
    @Column(name = "veiculo_ano", length = 8)
    private LocalDate anoFabricacao;

}
