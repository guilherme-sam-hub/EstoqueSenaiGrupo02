package edu.estoque.senai.grupo02.dtos.responses;

import edu.estoque.senai.grupo02.entities.Usuario;

public class UsuarioResponseDto {
    public Long id;
    public String nome;
    public String email;

    //Da entity para DTO (via construtor)
    public UsuarioResponseDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
