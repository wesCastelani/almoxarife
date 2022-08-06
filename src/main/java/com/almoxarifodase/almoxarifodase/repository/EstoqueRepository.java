package com.almoxarifodase.almoxarifodase.repository;

import com.almoxarifodase.almoxarifodase.model.entities.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {


    Estoque findByNomeEstoque(String nomeEstoque);
}
