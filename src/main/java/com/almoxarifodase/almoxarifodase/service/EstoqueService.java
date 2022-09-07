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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemEstoqueService itemEstoqueService;

    //Retorna um estoque buscado pelo seu nome
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

    //Quando um item não existe no estoque ele é criado aqui!
    public void criaItemNovoNoEstoque(String nomeEstoque, String nomeItem, Double qtd){
        Estoque estoque = estoqueRepository.findByNomeEstoque(nomeEstoque);
        Item item = itemRepository.findByName(nomeItem);
        ItemEstoque itemEstoque = itemEstoqueService.CriaUmItemEstoque(item, qtd);
        estoque.getItensEmEstoque().add(itemEstoque);
        estoqueRepository.save(estoque);
    }

    //Quanto um item ja existe no estoque aqui é onde ele ADICIONADO (qtd alterada)
    public void adicionaItemNoEstoque(String nomeEstoque, String nomeItem, Double qtd){
        Estoque estoque = estoqueRepository.findByNomeEstoque(nomeEstoque);
        estoque.getItensEmEstoque().forEach(i->{
            if(i.getItem().getName().equals(nomeItem)){
                i.setQtd(i.getQtd()+qtd);
            }
        });
        estoqueRepository.save(estoque);
    }

    //Aqui é onde é feito a retirada de itens
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

    //Busca no banco de dados o estoque pelo ID e o deleta se existente (Retorna true para deletado e false para notfound)
    @Transactional
    public Boolean delete(Long id) {
        Optional<Estoque> estoque = estoqueRepository.findById(id);
        if(estoque.isPresent()){
            estoqueRepository.delete(estoque.get());
            return true;
        }
        return false;
    }


    //Converte o objeto ItemForm informado pelo usuário para o objeto Estoque
    private Estoque convertToEstoque(EstoqueForm form) {
        Estoque estoque = new Estoque();
        estoque.setNomeEstoque(form.getName());
        return estoque;
    }

    //Converte um objeto Estoque para EstoqueDTO para retornos
    private EstoqueDTO convertToDTO(Estoque estoque){
        return new EstoqueDTO(estoque);
    }

    //Converte uma lista de Estoques para EstoquesDTO para retornos
    private static List<EstoqueDTO> convertListToDTO(List<Estoque> estoques) {
        return estoques.stream().map(EstoqueDTO::new).collect(Collectors.toList());
    }


}
