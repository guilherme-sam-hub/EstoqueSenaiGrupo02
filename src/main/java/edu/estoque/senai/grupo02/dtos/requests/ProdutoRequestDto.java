package edu.estoque.senai.grupo02.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProdutoRequestDto {
    @NotBlank
    public String nome;

    public String descricao;

    @NotNull
    public Integer quantidade;

    @NotNull
    public BigDecimal preco;

}
