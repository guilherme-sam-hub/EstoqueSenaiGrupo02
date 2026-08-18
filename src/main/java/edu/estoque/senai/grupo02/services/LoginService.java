package edu.estoque.senai.grupo02.services;
import edu.estoque.senai.grupo02.entities.Usuario;
import edu.estoque.senai.grupo02.dtos.requests.LoginRequestDto;
import edu.estoque.senai.grupo02.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LoginService {
    private final UsuarioRepository usuarioRepository;

    public LoginService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean autenticar(LoginRequestDto loginRequest) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuario.isEmpty()) {
            return false;
        }

        return usuario.get().getSenha().equals(loginRequest.getSenha());
    }

}
