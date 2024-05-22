package com.example.springsecurity.service;

import com.example.springsecurity.model.Produto;
import com.example.springsecurity.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listAll(){
        return produtoRepository.findAll();
    }

    public Produto criar(Produto request){
        return produtoRepository.save(request);
    }

    public void deletar(Long id){

        var produtoASerDeletado = produtoRepository.findById(id);

        if (produtoASerDeletado.isPresent()){
            produtoRepository.delete(produtoASerDeletado.get());
        }

    }
}
