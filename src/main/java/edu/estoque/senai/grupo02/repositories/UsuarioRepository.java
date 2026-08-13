package edu.estoque.senai.grupo02.repositories;

import edu.estoque.senai.grupo02.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Produto, Long> {

}
