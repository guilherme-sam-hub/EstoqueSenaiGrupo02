package edu.estoque.senai.grupo02.dtos.responses;

import edu.estoque.senai.grupo02.entities.Produto;

import java.math.BigDecimal;

public class ProdutoResponseDto {
    public Long id;
    public String nome;
    public String descricao;
    public Integer quantidade;
    public BigDecimal preco;

    public ProdutoResponseDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.quantidade = produto.getQuantidade();
        this.preco = produto.getPreco();
    }
}
