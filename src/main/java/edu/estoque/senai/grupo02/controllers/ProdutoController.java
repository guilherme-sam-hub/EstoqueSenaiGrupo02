package edu.estoque.senai.grupo02.controllers;

import edu.estoque.senai.grupo02.dtos.requests.ProdutoRequestDto;
import edu.estoque.senai.grupo02.dtos.responses.ProdutoResponseDto;
import edu.estoque.senai.grupo02.entities.Produto;
import edu.estoque.senai.grupo02.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // LISTAR TODOS (Utilizando seu DTO de Resposta)
    @GetMapping
    public List<ProdutoResponseDto> listarTodos() {
        return produtoService.listarTodos()
                .stream()
                .map(ProdutoResponseDto::new)
                .toList();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(produto -> ResponseEntity.ok(new ProdutoResponseDto(produto)))
                .orElse(ResponseEntity.notFound().build());
    }

    // CADASTRAR (Seu DTO + Ajustado para retornar Status 201 Created)
    @PostMapping
    public ResponseEntity<ProdutoResponseDto> cadastrar(@Valid @RequestBody ProdutoRequestDto dados) {
        Produto produto = new Produto();
        produto.setNome(dados.nome);
        produto.setDescricao(dados.descricao);
        produto.setQuantidade(dados.quantidade);
        produto.setPreco(dados.preco);

        Produto produtoSalvo = produtoService.salvar(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProdutoResponseDto(produtoSalvo));
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequestDto dados) {

        return produtoService.buscarPorId(id)
                .map(produto -> {
                    produto.setNome(dados.nome);
                    produto.setDescricao(dados.descricao);
                    produto.setQuantidade(dados.quantidade);
                    produto.setPreco(dados.preco);

                    Produto produtoAtualizado = produtoService.salvar(produto);

                    return ResponseEntity.ok(new ProdutoResponseDto(produtoAtualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (produtoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
