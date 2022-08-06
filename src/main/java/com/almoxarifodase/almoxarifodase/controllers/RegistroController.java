package com.almoxarifodase.almoxarifodase.controllers;


import com.almoxarifodase.almoxarifodase.model.DTO.RegistroDTO;
import com.almoxarifodase.almoxarifodase.model.forms.RegistroForm;
import com.almoxarifodase.almoxarifodase.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
@RestController
public class RegistroController {

    @Autowired
    RegistroService service;

    @GetMapping(value = "/registros/{nomeCanteiro}")
    public ResponseEntity<List<RegistroDTO>> listarRegistros(@PathVariable String nomeCanteiro){
        List<RegistroDTO> list = service.findByNomeCanteiro(nomeCanteiro);
        return ResponseEntity.ok().body(list);
    }


    @PostMapping(value = "/criarItem")
    public ResponseEntity<RegistroDTO> criarItem(@RequestBody @Valid RegistroForm form){
        return ResponseEntity.ok().body(service.criar(form));
    }

    @PostMapping(value = "/adicionarItem")
    public ResponseEntity<RegistroDTO> adicionarItem(@RequestBody @Valid RegistroForm form){
        return ResponseEntity.ok().body(service.adicionar(form));
    }

    @PostMapping(value = "/retirarItem")
    public ResponseEntity<RegistroDTO> retirarItem(@RequestBody @Valid RegistroForm form) {
        try {
            return ResponseEntity.ok().body(service.retirar(form));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "A Quantidade em estoque não é suficiente");

        }
    }


}
