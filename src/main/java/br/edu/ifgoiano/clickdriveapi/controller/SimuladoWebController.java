package br.edu.ifgoiano.clickdriveapi.controller;

import br.edu.ifgoiano.clickdriveapi.model.OpcaoModel;
import br.edu.ifgoiano.clickdriveapi.model.QuestaoModel;
import br.edu.ifgoiano.clickdriveapi.model.SimuladoModel;
import br.edu.ifgoiano.clickdriveapi.service.SimuladoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/web/simulados")
public class SimuladoWebController {

    @Autowired
    private SimuladoService simuladoService;

    // --- 1. ABRIR A TELA DA PROVA ---
    @GetMapping("/{id}")
    public String abrirProva(@PathVariable Long id, Model model) {
        SimuladoModel simulado = simuladoService.buscarPorId(id);

        if (simulado == null) {
            // Se o ID não existir, ele tenta carregar uma página de erro (que você pode criar depois)
            return "erro";
        }

        // Injeta a prova completa (com as questões) no HTML
        model.addAttribute("simulado", simulado);

        // Retorna exatamente o nome do seu arquivo HTML (sem a extensão .html)
        return "painel-simulado";
    }

    // --- 2. RECEBER AS RESPOSTAS DO ALUNO (CORREÇÃO) ---
    @PostMapping("/{id}/finalizar")
    public String finalizarProva(@PathVariable Long id, @RequestParam Map<String, String> respostasForm, Model model) {
        SimuladoModel simulado = simuladoService.buscarPorId(id);
        int nota = 0;

        // Loop para corrigir cada questão
        for (QuestaoModel questao : simulado.getQuestoes()) {

            // Pega o ID da opção que o aluno marcou (vem do formulário HTML)
            String opcaoMarcadaIdStr = respostasForm.get("questao_" + questao.getId());

            if (opcaoMarcadaIdStr != null) {
                Long opcaoMarcadaId = Long.parseLong(opcaoMarcadaIdStr);

                // Verifica na lista de opções se aquela que ele marcou tem o 'correta == true'
                boolean acertou = questao.getOpcoes().stream()
                        .anyMatch(o -> o.getId().equals(opcaoMarcadaId) && o.getCorreta());

                if (acertou) nota++;
            }
        }

        // Atualiza o objeto Simulado com o resultado (Aprova com 70% ou mais)
        simulado.setPontuacao(nota);
        simulado.setAprovado(nota >= (simulado.getQuestoes().size() * 0.7));

        // Prepara os dados para a tela de resultado
        model.addAttribute("simulado", simulado);
        model.addAttribute("totalQuestoes", simulado.getQuestoes().size());

        // Retorna exatamente o nome da sua tela de resultado
        return "painel-resultado";
    }
}