package edu.estoque.senai.grupo02.controllers;

import edu.estoque.senai.grupo02.dtos.requests.LoginRequestDto;
import edu.estoque.senai.grupo02.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login") // Define a rota base para o login
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        boolean autenticado = loginService.autenticar(loginRequest);

        if (!autenticado) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("mensagem",
                    "Email ou senha incorretos!"));
        }
        return ResponseEntity.ok(Map.of("mensagem", "Login realizado com sucesso!"));
    }
}
