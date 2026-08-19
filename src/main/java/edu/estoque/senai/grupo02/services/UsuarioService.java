package edu.estoque.senai.grupo02.services;

import edu.estoque.senai.grupo02.dtos.requests.LoginRequestDto;
import edu.estoque.senai.grupo02.dtos.requests.UsuarioRequestDto;
import edu.estoque.senai.grupo02.dtos.responses.UsuarioResponseDto;
import edu.estoque.senai.grupo02.entities.Usuario;
import edu.estoque.senai.grupo02.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponseDto salvar(UsuarioRequestDto dadosCadastro){
        if (usuarioRepository.findByEmail(dadosCadastro.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }
        //Da DTO pra Entity
        Usuario usuario = new Usuario();
        usuario.setNome(dadosCadastro.getNome());
        usuario.setEmail(dadosCadastro.getEmail());
        usuario.setSenha(dadosCadastro.getSenha());
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioResponseDto(usuarioSalvo);
    }
    public List<UsuarioResponseDto> listarTodos(){
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDto::new)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDto buscarPorId(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UsuarioResponseDto(usuario);
    }
    public UsuarioResponseDto atualizar(Long id, UsuarioRequestDto dadosCadastro) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.findByEmail(dadosCadastro.getEmail()).ifPresent(usuarioExistente -> {
            if (!usuarioExistente.getId().equals(id)) {
                throw new RuntimeException("E-mail já cadastrado");
            }
        });
        usuario.setNome(dadosCadastro.getNome());
        usuario.setEmail(dadosCadastro.getEmail());
        usuario.setSenha(dadosCadastro.getSenha());
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return new UsuarioResponseDto(usuarioAtualizado);
    }
    public void deletar(Long id){
        if(!usuarioRepository.existsById(id)){
            throw new RuntimeException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    }

