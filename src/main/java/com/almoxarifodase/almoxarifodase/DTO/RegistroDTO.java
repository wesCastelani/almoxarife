package com.almoxarifodase.almoxarifodase.DTO;

import com.almoxarifodase.almoxarifodase.entities.Registers;
import com.almoxarifodase.almoxarifodase.entities.TipoRegistro;

public class RegistroDTO {
    private Long id;
    private String nomeCanteiro;
    private String nomeItem;
    private Double qtd;
    private TipoRegistro tipo;

    public RegistroDTO() {
    }

    public RegistroDTO(Registers entity) {
        this.id = entity.getId();
        this.nomeCanteiro = entity.getNomeCanteiro();
        this.qtd = entity.getQtd();
        this.nomeItem = entity.getNomeItem();
        this.tipo = entity.getTipo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCanteiro() {
        return nomeCanteiro;
    }

    public void setNomeCanteiro(String nomeCanteiro) {
        this.nomeCanteiro = nomeCanteiro;
    }

    public Double getQtd() {
        return qtd;
    }

    public void setQtd(Double qtd) {
        this.qtd = qtd;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public TipoRegistro getTipo() {
        return tipo;
    }

    public void setTipo(TipoRegistro tipo) {
        this.tipo = tipo;
    }
}