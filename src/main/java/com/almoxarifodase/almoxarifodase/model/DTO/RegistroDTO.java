package com.almoxarifodase.almoxarifodase.model.DTO;

import com.almoxarifodase.almoxarifodase.model.entities.Registro;
import com.almoxarifodase.almoxarifodase.model.entities.TipoRegistro;
import lombok.Data;

import java.time.Instant;

@Data
public class RegistroDTO {
    private Long id;
    private String nomeEstoque;
    private String nomeItem;
    private Double qtd;
    private TipoRegistro tipo;
    private Instant moment;

    public RegistroDTO() {
    }

    public RegistroDTO(Registro entity) {
        this.id = entity.getId();
        this.nomeEstoque = entity.getNomeEstoque();
        this.qtd = entity.getQtd();
        this.nomeItem = entity.getNomeItem();
        this.tipo = entity.getTipo();
        this.moment = entity.getMoment();
    }
}
