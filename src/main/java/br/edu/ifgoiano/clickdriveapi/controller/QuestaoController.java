package br.edu.ifgoiano.clickdriveapi.controller;

import br.edu.ifgoiano.clickdriveapi.dto.QuestaoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.QuestaoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.mapper.QuestaoMapper;
import br.edu.ifgoiano.clickdriveapi.service.QuestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questoes")
public class QuestaoController {

    @Autowired
    private QuestaoService questaoService;

    @Autowired
    private QuestaoMapper questaoMapper;

    @PostMapping
    public ResponseEntity<QuestaoResponseDTO> criarQuestao(@RequestBody QuestaoRequestDTO novaQuestao) {

        System.out.println("Enunciado recebido: " + novaQuestao.enunciado());

        var questaoSalva = questaoService.salvarQuestao(questaoMapper.toEntity(novaQuestao));
        return ResponseEntity.status(HttpStatus.CREATED).body(questaoMapper.toResponseDTO(questaoSalva));
    }

    @GetMapping
    public ResponseEntity<List<QuestaoResponseDTO>> listarTodas() {
        return ResponseEntity.ok(questaoService.listarTodas().stream()
                .map(questaoMapper::toResponseDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestaoResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<QuestaoResponseDTO> questao = questaoService.buscarPorId(id)
                .map(questaoMapper::toResponseDTO);
        return questao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarQuestao(@PathVariable Long id) {
        questaoService.deletarQuestao(id);
        return ResponseEntity.noContent().build();
    }
}
