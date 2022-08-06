package com.almoxarifodase.almoxarifodase.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "tb_estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name_estoque", nullable = false, unique = true)
    private String nomeEstoque;

    @OneToMany
    private Set<ItemEstoque> itensEmEstoque = new HashSet<>();

}
