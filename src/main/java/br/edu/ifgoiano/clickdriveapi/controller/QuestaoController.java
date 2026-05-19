package br.edu.ifgoiano.clickdriveapi.controller;

import br.edu.ifgoiano.clickdriveapi.dto.QuestaoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.QuestaoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.service.QuestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questoes")
public class QuestaoController {

    @Autowired
    private QuestaoService questaoService;

    @PostMapping
    public ResponseEntity<QuestaoResponseDTO> criarQuestao(@RequestBody QuestaoRequestDTO novaQuestao) {

        System.out.println("Enunciado recebido: " + novaQuestao.enunciado());

        return ResponseEntity.status(HttpStatus.CREATED).body(questaoService.salvarQuestao(novaQuestao));
    }

    @GetMapping
    public ResponseEntity<List<QuestaoResponseDTO>> listarTodas() {
        return ResponseEntity.ok(questaoService.listarTodasDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return questaoService.buscarPorIdDTO(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarQuestao(@PathVariable Long id) {
        questaoService.deletarQuestao(id);
        return ResponseEntity.noContent().build();
    }
}
