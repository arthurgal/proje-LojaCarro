package com.example.springsecurity.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "nome")
    private String nome;

    @Column(name = "urlImagem")
    private String urlImagem;

    @Column(name = "marca")
    private String marca;

    @Column(name = "preco")
    private Double preco;
}
