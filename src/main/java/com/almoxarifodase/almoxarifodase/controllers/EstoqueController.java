package com.almoxarifodase.almoxarifodase.controllers;

import com.almoxarifodase.almoxarifodase.model.DTO.EstoqueDTO;
import com.almoxarifodase.almoxarifodase.model.forms.EstoqueForm;
import com.almoxarifodase.almoxarifodase.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    //Busca o estoque no banco de dados pelo seu nome
    @GetMapping(value = "/estoque/{name}")
    public ResponseEntity<EstoqueDTO> findOne(@PathVariable String name){
        EstoqueDTO estoqueDTO = estoqueService.findOne(name);
        return ResponseEntity.ok().body(estoqueDTO);
    }

    //Cria um estoque no banco de dados
    @PostMapping(value = "/estoque")
    public ResponseEntity<EstoqueDTO> insert(@RequestBody @Valid EstoqueForm form){
        EstoqueDTO dto = estoqueService.insert(form);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    //Deleta um estoque baseado no ID
    @DeleteMapping(path = "/estoque/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable @Valid Long id){

        Boolean foiDeletado = estoqueService.delete(id);

        if(!foiDeletado){
            String naoEncontrado = "Estoque de id " + id + " não encontrado!";
            return new ResponseEntity<>(naoEncontrado, HttpStatus.NOT_FOUND);
        }
        String Encontrado = "Estoque de id " + id + " foi deletado com sucesso!";
        return new ResponseEntity<>(Encontrado, HttpStatus.OK);
    }

}
