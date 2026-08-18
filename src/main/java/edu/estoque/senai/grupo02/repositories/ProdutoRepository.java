package edu.estoque.senai.grupo02.repositories;

import edu.estoque.senai.grupo02.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}