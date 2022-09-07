package com.almoxarifodase.almoxarifodase.model.forms;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ItemForm {

    @NotBlank(message = "Nome do item não pode estar em branco")
    private String name;
}
