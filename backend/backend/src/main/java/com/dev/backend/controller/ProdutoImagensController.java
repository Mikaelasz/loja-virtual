package com.dev.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.backend.entity.ProdutoImagens;
import com.dev.backend.service.ProdutoImagensServices;



@RestController
@RequestMapping("api/produtoImagens")
public class ProdutoImagensController {

    @Autowired
    private ProdutoImagensServices produtoImagensService;

    @GetMapping("/")
    public List<ProdutoImagens> buscarTodos(){
        return produtoImagensService.buscarTodos();
    }

    @PostMapping("/")
    public ProdutoImagens inserir(@RequestParam("idProduto") Long idProduto, @RequestParam("file") MultipartFile file){
        return produtoImagensService.inserir(idProduto, file);
    }

    @PutMapping("/")
    public ProdutoImagens alterar(ProdutoImagens categoria){
        return produtoImagensService.alterar(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        produtoImagensService.excluir(id);
        return ResponseEntity.ok().build();

    }

    
}
