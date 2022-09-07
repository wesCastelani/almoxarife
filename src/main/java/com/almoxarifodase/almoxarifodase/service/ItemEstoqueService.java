package com.almoxarifodase.almoxarifodase.service;

import com.almoxarifodase.almoxarifodase.model.entities.Estoque;
import com.almoxarifodase.almoxarifodase.model.entities.Item;
import com.almoxarifodase.almoxarifodase.model.entities.ItemEstoque;
import com.almoxarifodase.almoxarifodase.repository.ItemEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ItemEstoqueService {

    @Autowired
    ItemEstoqueRepository itemEstoqueRepository;


    //Adiciona um NOVO item no estoque
    public ItemEstoque CriaUmItemEstoque(Item item, Double qtd){
        ItemEstoque itemEstoque = new ItemEstoque(item, qtd);
        itemEstoqueRepository.save(itemEstoque);
        return itemEstoque;
    }
}
