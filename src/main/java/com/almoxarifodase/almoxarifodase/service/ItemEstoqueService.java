package com.almoxarifodase.almoxarifodase.service;

import com.almoxarifodase.almoxarifodase.model.entities.Item;
import com.almoxarifodase.almoxarifodase.model.entities.ItemEstoque;
import com.almoxarifodase.almoxarifodase.repository.ItemEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemEstoqueService {

    @Autowired
    ItemEstoqueRepository itemEstoqueRepository;

    public ItemEstoque CriaUmItemEstoque(Item item, Double qtd){
        ItemEstoque itemEstoque = new ItemEstoque(item, qtd);
        itemEstoqueRepository.save(itemEstoque);
        return itemEstoque;
    }
}
