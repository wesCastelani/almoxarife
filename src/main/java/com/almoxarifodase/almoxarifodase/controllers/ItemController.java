package com.almoxarifodase.almoxarifodase.controllers;

import com.almoxarifodase.almoxarifodase.model.DTO.ItemDTO;
import com.almoxarifodase.almoxarifodase.model.forms.ItemForm;
import com.almoxarifodase.almoxarifodase.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService service;

    //Lista de itens existentes
    @GetMapping(path = "/itens/listar")
    public ResponseEntity<List<ItemDTO>> findAll(){
        List<ItemDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    //Criar item
    @PostMapping(path = "/itens/criar")
    public ResponseEntity<ItemDTO> insert(@RequestBody @Valid ItemForm form){
        ItemDTO dto = service.insert(form);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("").buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    //Deletar um item
    @DeleteMapping(path = "/itens/deletar/{id}")
    public ResponseEntity<String> delete(@PathVariable @Valid Long id){

        Boolean foiDeletado = service.delete(id);

        if(!foiDeletado){
            String naoEncontrado = "item de id " + id + " não encontrado!";
            return new ResponseEntity<>(naoEncontrado, HttpStatus.NOT_FOUND);
        }
        String Encontrado = "item de id " + id + " foi deletado com sucesso!";
        return new ResponseEntity<>(Encontrado, HttpStatus.OK);
    }

}
