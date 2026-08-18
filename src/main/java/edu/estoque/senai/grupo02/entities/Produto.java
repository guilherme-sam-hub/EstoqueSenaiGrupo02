/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estoque.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table (name = "produto")

public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable =false, length = 100)
    private String nome;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
    
    @Column(nullable = false)
    private Integer quantidade;
    
    public Produto() {}
    
    public Produto (String nome, String descricao, BigDecimal preco, Integer quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        
    }
    
    public Long getId() {
        return id;

    } 
    
    public void setId (Long id) {
        this.id = id;
        
    }
    
    public String getNome () {
            return nome;
       
    }
    
    
    public void setNome( String nome) {
        this.nome = nome;
        
    }
    
    public String getDescricao () {
       return descricao;
       
    }
    
    public void setDescricao ( String descricao) {
        this.descricao = descricao;
        
    }
    
    public BigDecimal getPreco (){
        return preco;
                
    }
    
    public void setPreco( BigDecimal preco) {
        this.preco = preco;
        
    }
    
    public Integer getQuantidade () {
        
        return quantidade;
    }  
    
    public void setQuantidade ( Integer quantidade) {
        this.quantidade = quantidade;
        
    }
}
