package com.dev.backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.backend.entity.Produto;
import com.dev.backend.entity.ProdutoImagens;
import com.dev.backend.repository.ProdutoImagensRepository;
import com.dev.backend.repository.ProdutoRepository;

@Service
public class ProdutoImagensServices {

    @Autowired
    public ProdutoImagensRepository produtoImagensRepository;

    @Autowired
    public ProdutoRepository produtoRepository;

    public List<ProdutoImagens> buscarTodos(){
        return produtoImagensRepository.findAll();        
    }

    public ProdutoImagens inserir(Long  idProduto, MultipartFile file){
        Produto produto = produtoRepository.findById(idProduto).get();
        ProdutoImagens novoProdutoImagens = new ProdutoImagens();

        try{
            if(!file.isEmpty()){
                byte[] bytes = file.getBytes();
                String nomeImagem = String.valueOf(produto.getId()) + file.getOriginalFilename();
                Path caminho = Paths
                        .get("c:/imagens/" + nomeImagem);
                Files.write(caminho, bytes);

                novoProdutoImagens.setNome(nomeImagem);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        novoProdutoImagens.setProduto(produto);
        novoProdutoImagens.setDataCriacao(new Date());
        novoProdutoImagens = produtoImagensRepository.saveAndFlush(novoProdutoImagens);
        return novoProdutoImagens;       
    }

    public ProdutoImagens alterar(ProdutoImagens categoria){
        categoria.setDataAtualizacao(new Date());
        return produtoImagensRepository.saveAndFlush(categoria);
    }

    public void excluir(Long id){
        ProdutoImagens produtoImagens = produtoImagensRepository.findById(id).get();
        produtoImagensRepository.delete(produtoImagens); 
    }
    
}
