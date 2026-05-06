package br.edu.ifgoiano.clickdriveapi.controller;

import br.edu.ifgoiano.clickdriveapi.model.CandidaturaModel;
import br.edu.ifgoiano.clickdriveapi.model.StatusCandidatura;
import br.edu.ifgoiano.clickdriveapi.service.CandidaturaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class AdminViewController {

    // Injete o serviço que você já tem
    private final CandidaturaService candidaturaService;

    public AdminViewController(CandidaturaService candidaturaService) {
        this.candidaturaService = candidaturaService;
    }

    @GetMapping("/painel-admin")
    public String exibirPainelAdmin(Model model) {
        // Busca as candidaturas pendentes usando a regra de negócio que você já criou
        List<CandidaturaModel> pendentes = candidaturaService.findByStatus(StatusCandidatura.PENDENTE);


        // Envia a lista para a tela HTML com o nome "listaCandidaturas"
        model.addAttribute("listaCandidaturas", pendentes);

        // Retorna o nome do arquivo HTML (sem o .html)
        return "painel-admin";
    }
}