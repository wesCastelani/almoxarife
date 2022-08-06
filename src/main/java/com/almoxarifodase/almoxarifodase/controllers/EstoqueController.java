package com.almoxarifodase.almoxarifodase.controllers;

import com.almoxarifodase.almoxarifodase.model.DTO.EstoqueDTO;
import com.almoxarifodase.almoxarifodase.model.forms.EstoqueForm;
import com.almoxarifodase.almoxarifodase.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @GetMapping(value = "/estoque")
    public ResponseEntity<List<EstoqueDTO>> findAll(){
        List<EstoqueDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/estoque/{name}")
    public ResponseEntity<EstoqueDTO> findOne(@PathVariable String name){
        EstoqueDTO estoqueDTO = service.findOne(name);
        return ResponseEntity.ok().body(estoqueDTO);
    }

    @PostMapping(value = "/estoque")
    public ResponseEntity<EstoqueDTO> insert(@RequestBody @Valid EstoqueForm form){
        EstoqueDTO dto = service.insert(form);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
