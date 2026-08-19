<<<<<<< HEAD
package edu.estoque.senai.grupo02.controllers;

import edu.estoque.senai.grupo02.dtos.requests.LoginRequestDto;
import edu.estoque.senai.grupo02.dtos.requests.UsuarioRequestDto;
import edu.estoque.senai.grupo02.dtos.responses.UsuarioResponseDto;
import edu.estoque.senai.grupo02.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
<<<<<<< HEAD
    public ResponseEntity<UsuarioResponseDto> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
=======
    public ResponseEntity<List<UsuarioResponseDto>> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscaPorId(id));
>>>>>>> 0bb299375592026fac7addfa1a4cda260ccf4763
    }
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> cadastrar(
            @Valid @RequestBody UsuarioRequestDto dadosCadastro
            ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.salvar(dadosCadastro));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDto dadosCadastro
    ) {
        return ResponseEntity.ok(usuarioService.atualizar(id, dadosCadastro));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dadosLogin){
        UsuarioResponseDto usuario = usuarioService.validarLogin(dadosLogin);
        if(usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Dados inválidos");
    }

}
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.estoque.senai.grupo02.controllers;

import edu.estoque.senai.grupo02.entities.Usuario;
import edu.estoque.senai.grupo02.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
>>>>>>> 5b203bfdb9fa120c621984a5126445b165751c6a
