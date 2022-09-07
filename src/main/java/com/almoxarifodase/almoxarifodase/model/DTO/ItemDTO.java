package com.almoxarifodase.almoxarifodase.model.DTO;

import com.almoxarifodase.almoxarifodase.model.entities.Item;
import lombok.Data;

@Data
public class ItemDTO {

    private Long id;
    private String name;


    public ItemDTO(){}

    public ItemDTO(Item entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
