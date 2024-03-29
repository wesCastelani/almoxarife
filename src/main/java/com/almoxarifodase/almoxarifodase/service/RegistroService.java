package com.almoxarifodase.almoxarifodase.service;

import com.almoxarifodase.almoxarifodase.model.DTO.RegistroDTO;
import com.almoxarifodase.almoxarifodase.model.entities.Registro;
import com.almoxarifodase.almoxarifodase.model.forms.RegistroForm;
import com.almoxarifodase.almoxarifodase.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class RegistroService {
    @Autowired
    RegistroRepository registroRepository;

    @Autowired
    EstoqueService estoqueService;

    //Cria um registro no momento em que o item é criado no estoque
    @Transactional
    public RegistroDTO criar(RegistroForm form){
        Registro register = convertToRegistro(form ,Instant.now());
        estoqueService.criaItemNovoNoEstoque(form.getNomeEstoque(), form.getNomeItem(), form.getQtd());
        registroRepository.save(register);
        return convertToDTO(register);
    }

    //Cria um registro sempre que uma adição é feita a algum item
    @Transactional
    public RegistroDTO adicionar(RegistroForm form){
        Registro register = convertToRegistro(form ,Instant.now());
        estoqueService.adicionaItemNoEstoque(form.getNomeEstoque(), form.getNomeItem(), form.getQtd());
        registroRepository.save(register);
        return convertToDTO(register);
    }

    //Cria um registro de retirada sempre que ela é realizada
    @Transactional
    public RegistroDTO retirar(RegistroForm form) throws Exception {
        Registro register = convertToRegistro(form ,Instant.now());
        estoqueService.retiraItemDoEstoque(form.getNomeEstoque(), form.getNomeItem(), form.getQtd());
        registroRepository.save(register);
        return convertToDTO(register);
    }

    //Lista todos os registros de um determinado estoque baseado no seu nome
    @Transactional
    public List<RegistroDTO> findByNomeCanteiro(String nomeEstoque) {
        List<Registro> list = registroRepository.findByNomeEstoque(nomeEstoque);
        return list.stream().map(x -> new RegistroDTO(x)).collect(Collectors.toList());
    }

    //Converte um objeto RegistroForm para um objeto Registro para cria-lo no banco
    private Registro convertToRegistro(RegistroForm form, Instant moment){
        Registro registro = new Registro();
        registro.setNomeEstoque(form.getNomeEstoque());
        registro.setNomeItem(form.getNomeItem());
        registro.setQtd(form.getQtd());
        registro.setTipo(form.getTipo());
        registro.setMoment(moment);
        return registro;
    }

    //Converte um objeto Registro para RegistroDTO para retornos
    private RegistroDTO convertToDTO(Registro registro){
        return new RegistroDTO(registro);
    }


}
