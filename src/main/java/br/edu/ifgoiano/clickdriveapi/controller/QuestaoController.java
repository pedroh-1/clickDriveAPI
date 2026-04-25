package br.edu.ifgoiano.clickdriveapi.controller;

import br.edu.ifgoiano.clickdriveapi.model.QuestaoModel;
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

    @PostMapping
    public ResponseEntity<QuestaoModel> criarQuestao(@RequestBody QuestaoModel novaQuestao) {

        System.out.println("Enunciado recebido: " + novaQuestao.getEnunciado());

        QuestaoModel questaoSalva = questaoService.salvarQuestao(novaQuestao);
        return ResponseEntity.status(HttpStatus.CREATED).body(questaoSalva);
    }

    @GetMapping
    public ResponseEntity<List<QuestaoModel>> listarTodas() {
        return ResponseEntity.ok(questaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestaoModel> buscarPorId(@PathVariable Long id) {
        Optional<QuestaoModel> questao = questaoService.buscarPorId(id);
        return questao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarQuestao(@PathVariable Long id) {
        questaoService.deletarQuestao(id);
        return ResponseEntity.noContent().build();
    }
}
