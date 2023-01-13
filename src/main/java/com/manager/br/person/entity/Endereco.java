package com.manager.br.person.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "logradouro", nullable = false)
    private String logradouro;
    @Column(name = "cep", nullable = false)
    private String cep;
    @Column(name = "numero", nullable = false)
    private Long numero;
    @Column(name ="cidade",nullable = false )
    private String cidade;
    @Column  (name = "principal")
    private boolean principal;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Pessoa pessoa;

}

