package com.almoxarifodase.almoxarifodase.service;

import com.almoxarifodase.almoxarifodase.model.DTO.ItemDTO;
import com.almoxarifodase.almoxarifodase.model.entities.Item;
import com.almoxarifodase.almoxarifodase.model.forms.ItemForm;
import com.almoxarifodase.almoxarifodase.repository.ItemRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    //Lista todos os itens cadastrados no banco de dados
    @Transactional
    public List<ItemDTO> findAll(){
        List<Item> list = repository.findAll();
        return list.stream().map(x -> new ItemDTO(x)).collect(Collectors.toList());
    }

    //Adiciona um novo item no banco de dados e o retorna pra o usuário
    @Transactional
    public ItemDTO insert(ItemForm form){
        Item item = convertToItem(form);
        item = repository.save(item);
        return new ItemDTO(item);
    }

    //Busca o item que possui o ID informado e remove se ele existir retornando TRUE para deletado e FALSE para NOTFOUND
    @Transactional
    public Boolean delete(Long id) {
        Optional<Item> item = repository.findById(id);
        if(item.isPresent()){
            repository.delete(item.get());
            return true;
        }
        return false;
    }


    //Converte o objeto ItemForm informado pelo usuário para Item, para que seja adicionado no banco
    private Item convertToItem(ItemForm form){
        Item item = new Item();
        item.setName(form.getName());
        return item;
    }

    //Cria um novo itemDTO para ser retornado nas funções
    private ItemDTO convertToDto(Item item){
        return new ItemDTO(item);
    }
}
