package br.edu.ifgoiano.clickdriveapi.controller;

import br.edu.ifgoiano.clickdriveapi.model.UsuarioModel;
import br.edu.ifgoiano.clickdriveapi.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Exibe a tela de login no navegador
    @GetMapping("/web/login")
    public String telaLogin() {
        return "login";
    }

    // Processa o formulário de login
    @PostMapping("/login")
    public String autenticar(
            @RequestParam("email") String email,
            @RequestParam("senha") String senha,
            Model model) {

        // Busca o usuário pelo e-mail na tabela pai 'usuarios'
        Optional<UsuarioModel> usuarioOpt = usuarioRepository.findAll().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();

        if (usuarioOpt.isPresent()) {
            UsuarioModel usuario = usuarioOpt.get();

            // Validação simples de senha (em produção usaríamos BCrypt)
            if (usuario.getSenha().equals(senha)) {

                // REGRA DE NEGÓCIO DE REDIRECIONAMENTO:
                if (email.contains("admin")) {
                    return "redirect:/admin/painel"; // Se for admin, vai pro painel de aprovação
                }

                return "redirect:/usuarios"; // Se for comum, vai para outra rota do sistema
            }
        }

        // Se falhar, devolve para a tela de login com uma mensagem de erro
        model.addAttribute("erro", "E-mail ou senha incorretos.");
        return "login";
    }
}