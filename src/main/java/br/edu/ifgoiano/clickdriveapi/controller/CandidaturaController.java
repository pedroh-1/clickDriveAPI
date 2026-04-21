package br.edu.ifgoiano.clickdriveapi.controller;

import br.edu.ifgoiano.clickdriveapi.model.CandidaturaModel;
import br.edu.ifgoiano.clickdriveapi.service.CandidaturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/candidaturas")
public class CandidaturaController {

    private final CandidaturaService candidaturaService;

    public CandidaturaController(CandidaturaService candidaturaService) {
        this.candidaturaService = candidaturaService;
    }

    @PostMapping
    public ResponseEntity<CandidaturaModel> criar(@RequestBody CandidaturaModel candidatura) {
        return new ResponseEntity<>(candidaturaService.salvar(candidatura), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CandidaturaModel>> listarTodas() {
        // Agora passa a lista vinda do service
        return ResponseEntity.ok(candidaturaService.listarTodas());
    }

    @PatchMapping("/{id}/aprovar")
    public ResponseEntity<CandidaturaModel> aprovar(@PathVariable Long id) {
        return ResponseEntity.ok(candidaturaService.aprovarInstrutor(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        candidaturaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}