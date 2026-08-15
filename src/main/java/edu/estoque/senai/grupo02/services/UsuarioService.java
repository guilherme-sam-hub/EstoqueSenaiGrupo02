package edu.estoque.senai.grupo02.services;

import edu.estoque.senai.grupo02.dtos.requests.LoginRequestDto;
import edu.estoque.senai.grupo02.dtos.requests.UsuarioRequestDto;
import edu.estoque.senai.grupo02.entities.Usuario;
import edu.estoque.senai.grupo02.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(UsuarioRequestDto dadosCadastro){
        if (usuarioRepository.findByEmail(dadosCadastro.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }
        //Da DTO pra Entity
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dadosCadastro.getNome());
        novoUsuario.setEmail(dadosCadastro.getEmail());
        novoUsuario.setSenha(dadosCadastro.getSenha());

        return usuarioRepository.save(novoUsuario);
    }
    public Usuario validarLogin(LoginRequestDto dadosLogin){
        //busca no banco usuario<->email
        Optional<Usuario> usuarioPossivel = usuarioRepository.findByEmail(dadosLogin.getEmail());

        //se email existe
        if (usuarioPossivel.isPresent()) {
            Usuario usuario = usuarioPossivel.get();

            // se senha digitada <-> senha no banco
            if (usuario.getSenha().equals(dadosLogin.getSenha())) {
            return usuario; //login
            }
        }
        return null; //se falhar nas condicionais
    }
}
