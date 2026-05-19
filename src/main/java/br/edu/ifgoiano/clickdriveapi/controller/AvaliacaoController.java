package br.edu.ifgoiano.clickdriveapi.controller;


import br.edu.ifgoiano.clickdriveapi.dto.AvaliacaoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.AvaliacaoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    // --- CRIAR NOVA AVALIAÇÃO ---
    @PostMapping
    public ResponseEntity<?> criarAvaliacao(@RequestBody AvaliacaoRequestDTO avaliacao) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoService.salvarAvaliacao(avaliacao));
        } catch (IllegalArgumentException | IllegalStateException e) {
            // Se cair em alguma das regras de negócio, devolve o erro limpo pro usuário
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // --- VER AVALIAÇÃO DE UMA AULA ESPECÍFICA ---
    @GetMapping("/aula/{aulaId}")
    public ResponseEntity<AvaliacaoResponseDTO> buscarPorAula(@PathVariable Long aulaId) {
        var avaliacao = avaliacaoService.buscarPorAulaDTO(aulaId);
        if (avaliacao != null) {
            return ResponseEntity.ok(avaliacao);
        }
        return ResponseEntity.notFound().build();
    }

}
