package edu.estoque.senai.grupo02.repositories;

import edu.estoque.senai.grupo02.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Usuario, Long> {
}
