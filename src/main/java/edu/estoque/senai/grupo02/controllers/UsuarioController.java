package edu.estoque.senai.grupo02.controllers;

import edu.estoque.senai.grupo02.dtos.requests.UsuarioRequestDto;
import edu.estoque.senai.grupo02.dtos.responses.UsuarioResponseDto;
import edu.estoque.senai.grupo02.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadastro")
    public String exibirFormulario(Model model){
        model.addAttribute("usuario", new UsuarioRequestDto());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(
            @Valid @ModelAttribute("usuario") UsuarioRequestDto dadosCadastro,
            BindingResult result,
            Model model){
        //condicionais de validação
        if (result.hasErrors()){
            return "cadastro";
        }
        try {
            usuarioService.salvar(dadosCadastro);
            return "redirect:/login?sucesso";
        }catch (RuntimeException e) {
            model.addAttribute("Erro", e.getMessage());
            return "cadastro";
        }
    }
}
