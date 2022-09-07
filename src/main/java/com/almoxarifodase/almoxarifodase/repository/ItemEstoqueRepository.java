package com.almoxarifodase.almoxarifodase.repository;

import com.almoxarifodase.almoxarifodase.model.entities.Item;
import com.almoxarifodase.almoxarifodase.model.entities.ItemEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemEstoqueRepository extends JpaRepository<ItemEstoque, Long> {
}
