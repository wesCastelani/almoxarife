package com.almoxarifodase.almoxarifodase.model.forms;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EstoqueForm {

    @NotBlank(message = "Nome do estoque não pode estar em branco")
    private String name;

}
