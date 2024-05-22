package com.example.springsecurity.controller;

import com.example.springsecurity.model.Produto;
import com.example.springsecurity.service.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@CrossOrigin
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.listAll());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto){
        try{
            produtoService.criar(produto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> deletarProduto(@RequestParam Long id){
        try{
            produtoService.deletar(id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
