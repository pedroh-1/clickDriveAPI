package br.edu.ifgoiano.clickdriveapi.controller.webController;

import br.edu.ifgoiano.clickdriveapi.service.CandidaturaService;
import br.edu.ifgoiano.clickdriveapi.model.StatusCandidatura;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin") // Caminho base para acessar no navegador
public class CandidaturaWebController {

    private final CandidaturaService candidaturaService;

    // Construtor para injetar o service
    public CandidaturaWebController(CandidaturaService candidaturaService) {
        this.candidaturaService = candidaturaService;
    }

    @GetMapping("/painel") // Rota específica da tela
    public String abrirPainelAdmin(Model model) {
        // Busca as candidaturas pendentes e joga na lista que o Thymeleaf vai ler
        model.addAttribute("listaCandidaturas", candidaturaService.findByStatus(StatusCandidatura.PENDENTE));

        return "painel-admin"; // Retorna exatamente o nome do arquivo HTML sem o .html
    }
}