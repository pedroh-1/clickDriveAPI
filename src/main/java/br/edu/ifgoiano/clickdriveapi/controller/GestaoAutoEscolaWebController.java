package br.edu.ifgoiano.clickdriveapi.controller;

import br.edu.ifgoiano.clickdriveapi.dto.AvaliacaoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.DicaRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.OpcaoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.PagamentoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.QuestaoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.model.StatusPagamento;
import br.edu.ifgoiano.clickdriveapi.service.AvaliacaoService;
import br.edu.ifgoiano.clickdriveapi.service.DicaService;
import br.edu.ifgoiano.clickdriveapi.service.PagamentoService;
import br.edu.ifgoiano.clickdriveapi.service.QuestaoService;
import br.edu.ifgoiano.clickdriveapi.service.SimuladoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/web")
public class GestaoAutoEscolaWebController {

    private final SimuladoService simuladoService;
    private final QuestaoService questaoService;
    private final AvaliacaoService avaliacaoService;
    private final PagamentoService pagamentoService;
    private final DicaService dicaService;

    public GestaoAutoEscolaWebController(SimuladoService simuladoService,
                                         QuestaoService questaoService,
                                         AvaliacaoService avaliacaoService,
                                         PagamentoService pagamentoService,
                                         DicaService dicaService) {
        this.simuladoService = simuladoService;
        this.questaoService = questaoService;
        this.avaliacaoService = avaliacaoService;
        this.pagamentoService = pagamentoService;
        this.dicaService = dicaService;
    }

    @GetMapping
    public String inicio() {
        return "redirect:/web/simulados";
    }

    @GetMapping("/simulados")
    public String simulados(Model model) {
        model.addAttribute("activePage", "simulados");
        model.addAttribute("simulados", simuladoService.listarTodosDTO());
        return "gestao-simulados";
    }

    @PostMapping("/simulados/gerar")
    public String gerarSimulado(@RequestParam Long alunoId, RedirectAttributes redirectAttributes) {
        try {
            var simulado = simuladoService.gerarSimuladoDTO(alunoId);
            redirectAttributes.addFlashAttribute("success", "Simulado " + simulado.id() + " gerado para o aluno " + alunoId + ".");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/web/simulados";
    }

    @GetMapping("/questoes")
    public String questoes(Model model) {
        model.addAttribute("activePage", "questoes");
        model.addAttribute("questoes", questaoService.listarTodasDTO());
        return "gestao-questoes";
    }

    @PostMapping("/questoes")
    public String criarQuestao(@RequestParam String enunciado,
                               @RequestParam String categoria,
                               @RequestParam List<String> textos,
                               @RequestParam Integer corretaIndex,
                               RedirectAttributes redirectAttributes) {
        List<OpcaoRequestDTO> opcoes = new ArrayList<>();
        for (int i = 0; i < textos.size(); i++) {
            String texto = textos.get(i);
            if (texto != null && !texto.isBlank()) {
                opcoes.add(new OpcaoRequestDTO(null, texto, i == corretaIndex, null));
            }
        }

        try {
            questaoService.salvarQuestao(new QuestaoRequestDTO(null, enunciado, categoria, opcoes));
            redirectAttributes.addFlashAttribute("success", "Questao cadastrada com as opcoes informadas.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/web/questoes";
    }

    @GetMapping("/opcoes")
    public String opcoes(Model model) {
        model.addAttribute("activePage", "opcoes");
        model.addAttribute("questoes", questaoService.listarTodasDTO());
        return "gestao-opcoes";
    }

    @GetMapping("/avaliacoes")
    public String avaliacoes(Model model) {
        model.addAttribute("activePage", "avaliacoes");
        model.addAttribute("avaliacoes", avaliacaoService.listarTodasDTO());
        return "gestao-avaliacoes";
    }

    @PostMapping("/avaliacoes")
    public String criarAvaliacao(@RequestParam Integer nota,
                                 @RequestParam(required = false) String comentario,
                                 @RequestParam Long aulaId,
                                 RedirectAttributes redirectAttributes) {
        try {
            avaliacaoService.salvarAvaliacao(new AvaliacaoRequestDTO(null, nota, comentario, aulaId));
            redirectAttributes.addFlashAttribute("success", "Avaliacao cadastrada para a aula " + aulaId + ".");
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/web/avaliacoes";
    }

    @GetMapping("/pagamentos")
    public String pagamentos(Model model) {
        model.addAttribute("activePage", "pagamentos");
        model.addAttribute("pagamentos", pagamentoService.listarTodosDTO());
        return "gestao-pagamentos";
    }

    @PostMapping("/pagamentos")
    public String criarPagamento(@RequestParam Double valor,
                                 @RequestParam Long aulaId,
                                 RedirectAttributes redirectAttributes) {
        try {
            pagamentoService.gerarPagamento(new PagamentoRequestDTO(null, valor, StatusPagamento.PENDENTE, null, aulaId));
            redirectAttributes.addFlashAttribute("success", "Cobranca gerada para a aula " + aulaId + ".");
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/web/pagamentos";
    }

    @PostMapping("/pagamentos/{id}/confirmar")
    public String confirmarPagamento(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            pagamentoService.confirmarPagamentoDTO(id);
            redirectAttributes.addFlashAttribute("success", "Pagamento " + id + " confirmado.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/web/pagamentos";
    }

    @GetMapping("/dicas")
    public String dicas(Model model) {
        model.addAttribute("activePage", "dicas");
        model.addAttribute("dicas", dicaService.listarTodasDTO());
        return "gestao-dicas";
    }

    @PostMapping("/dicas")
    public String criarDica(@RequestParam String titulo,
                            @RequestParam String conteudo,
                            @RequestParam Long categoriaDicaId,
                            RedirectAttributes redirectAttributes) {
        try {
            dicaService.salvarDica(new DicaRequestDTO(null, titulo, conteudo, null, categoriaDicaId));
            redirectAttributes.addFlashAttribute("success", "Dica publicada.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/web/dicas";
    }
    // Exemplo de como receber os dados no seu Controller
    @PostMapping("/disponibilidades")
    public String criarDisponibilidade(@RequestParam Long instrutorId, @RequestParam String diasSemana,
                                       @RequestParam String horario, @RequestParam String horaFim) {
        // Chame seu Service aqui: disponibService.salvar(...)
        return "redirect:/web/agendamento";
    }

    @PostMapping("/aulas")
    public String criarAula(@RequestParam Long alunoId, @RequestParam Long instrutorId,
                            @RequestParam String data, @RequestParam String horario) {
        // Chame seu Service aqui: aulaService.salvar(...)
        return "redirect:/web/agendamento";
    }
}
