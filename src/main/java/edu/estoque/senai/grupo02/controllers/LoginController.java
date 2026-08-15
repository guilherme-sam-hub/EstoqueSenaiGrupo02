package edu.estoque.senai.grupo02.controllers;

import edu.estoque.senai.grupo02.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class LoginController {

    @Autowired
    public UsuarioService usuarioService;

}
