package com.almoxarifodase.almoxarifodase.model.DTO;

import com.almoxarifodase.almoxarifodase.model.entities.Estoque;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class EstoqueDTO {

    private Long id;
    private String nomeEstoque;
    private List<ItemEstoqueDTO> itens = new ArrayList<>();

    public EstoqueDTO(){}

    public EstoqueDTO(Estoque entitiy) {
        this.id = entitiy.getId();
        this.nomeEstoque = entitiy.getNomeEstoque();
        this.itens = entitiy.getItensEmEstoque().stream().map(x->new ItemEstoqueDTO(x)).collect(Collectors.toList());
    }
}
