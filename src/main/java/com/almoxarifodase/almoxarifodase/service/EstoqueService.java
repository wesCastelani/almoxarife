package com.almoxarifodase.almoxarifodase.service;

import com.almoxarifodase.almoxarifodase.model.DTO.EstoqueDTO;
import com.almoxarifodase.almoxarifodase.model.entities.Estoque;
import com.almoxarifodase.almoxarifodase.model.entities.Item;
import com.almoxarifodase.almoxarifodase.model.entities.ItemEstoque;
import com.almoxarifodase.almoxarifodase.model.forms.EstoqueForm;
import com.almoxarifodase.almoxarifodase.repository.EstoqueRepository;
import com.almoxarifodase.almoxarifodase.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemEstoqueService itemEstoqueService;

    @Transactional
    public List<EstoqueDTO> findAll(){
        List<Estoque> list = estoqueRepository.findAll();
        return convertListToDTO(list);
    }

    @Transactional
    public EstoqueDTO findOne(String name){
        Estoque estoque = estoqueRepository.findByNomeEstoque(name);
        return convertToDTO(estoque);
    }

    @Transactional
    public EstoqueDTO insert(EstoqueForm form){
        Estoque estoque = convertToEstoque(form);
        estoque = estoqueRepository.save(estoque);
        return new EstoqueDTO(estoque);
    }

    public void criaItemNovoNoEstoque(String nomeEstoque, String nomeItem, Double qtd){
        Estoque estoque = estoqueRepository.findByNomeEstoque(nomeEstoque);
        Item item = itemRepository.findByName(nomeItem);
        ItemEstoque itemEstoque = itemEstoqueService.CriaUmItemEstoque(item, qtd);
        estoque.getItensEmEstoque().add(itemEstoque);
        estoqueRepository.save(estoque);
    }

    public void adicionaItemNoEstoque(String nomeEstoque, String nomeItem, Double qtd){
        Estoque estoque = estoqueRepository.findByNomeEstoque(nomeEstoque);
        estoque.getItensEmEstoque().forEach(i->{
            if(i.getItem().getName().equals(nomeItem)){
                i.setQtd(i.getQtd()+qtd);
            }
        });
        estoqueRepository.save(estoque);
    }

    public void retiraItemDoEstoque(String nomeEstoque, String nomeItem, Double qtd){
        Estoque estoque = estoqueRepository.findByNomeEstoque(nomeEstoque);
        estoque.getItensEmEstoque().forEach(i -> {
            if (i.getItem().getName().equals(nomeItem)){
                if(i.getQtd() >= qtd){
                    i.setQtd(i.getQtd() - qtd);
                }else{
                    throw new RuntimeException();
                }
            }
        });
        estoqueRepository.save(estoque);
    }

    private Estoque convertToEstoque(EstoqueForm form) {
        Estoque estoque = new Estoque();
        estoque.setNomeEstoque(form.getName());
        return estoque;
    }
    @Transactional
    public void adicionaItemNoEstoque(Estoque e, ItemEstoque itens){
        e.getItensEmEstoque().add(itens);
        estoqueRepository.save(e);
    }

    private EstoqueDTO convertToDTO(Estoque estoque){
        return new EstoqueDTO(estoque);
    }

    private static List<EstoqueDTO> convertListToDTO(List<Estoque> estoques) {
        return estoques.stream().map(EstoqueDTO::new).collect(Collectors.toList());
    }


}
