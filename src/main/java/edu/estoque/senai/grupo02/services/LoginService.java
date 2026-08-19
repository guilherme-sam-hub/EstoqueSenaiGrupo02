package edu.estoque.senai.grupo02.services;
<<<<<<< HEAD

import edu.estoque.senai.grupo02.dtos.requests.LoginRequestDto;
import edu.estoque.senai.grupo02.entities.Usuario;
=======
import edu.estoque.senai.grupo02.entities.Usuario;
import edu.estoque.senai.grupo02.dtos.requests.LoginRequestDto;
>>>>>>> 5b203bfdb9fa120c621984a5126445b165751c6a
import edu.estoque.senai.grupo02.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LoginService {
<<<<<<< HEAD
    public final UsuarioRepository usuarioRepository;
=======
    private final UsuarioRepository usuarioRepository;
>>>>>>> 5b203bfdb9fa120c621984a5126445b165751c6a

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
