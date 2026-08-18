/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estoque.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")


/**
 *
 * @author sandy
 */
public class Usuario {

@Id

@GeneratedValue(strategy = GenerationType.IDENTITY)

private Long id;

@Column (nullable = false, length = 100)

private String nome;

@Column (nullable = false, unique = true, length = 100)

private String email;

@Column(nullable = false)

private String senha;

public Usuario (){}

public Usuario (String nome, String email, String senha){
    this.nome = nome;
    this.email = email;
    this.senha = senha;
}

public Long getId() {
    return id;
}
    
public void setId( Long id) {
    this.id = id;
}

public String getNome() {
        return nome;
}

public void setNome ( String nome) {
    this.nome = nome;
}

public String getEmail(){
    return email;
}

public void setEmail( String email) {
    this.email = email;
}

public  String getSenha (){
        return senha;
}

public void setSenha ( String senha ){
    this.senha =  senha;
}



}
