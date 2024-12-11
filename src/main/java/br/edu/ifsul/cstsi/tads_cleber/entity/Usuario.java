package br.edu.ifsul.cstsi.tads_cleber.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Usuarios")
@Table(name = "Usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    List<Corrida> Corridas = new ArrayList<>();


}