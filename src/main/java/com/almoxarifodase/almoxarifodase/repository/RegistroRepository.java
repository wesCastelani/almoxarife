package com.almoxarifodase.almoxarifodase.repository;

import com.almoxarifodase.almoxarifodase.model.entities.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroRepository extends JpaRepository<Registro, Long> {

    List<Registro> findByNomeEstoque(String nomeEstoque);
}
