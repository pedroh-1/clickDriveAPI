package br.edu.ifgoiano.clickdriveapi.controller;

import br.edu.ifgoiano.clickdriveapi.model.AulaModel;
import br.edu.ifgoiano.clickdriveapi.service.AulaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aulas")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @PostMapping
    public ResponseEntity<AulaModel> criarAula(@RequestBody AulaModel aula) {
        AulaModel novaAula = aulaService.salvar(aula);
        return new ResponseEntity<>(novaAula, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AulaModel>> listarTodas() {
        List<AulaModel> aulas = aulaService.listarTodas();
        return ResponseEntity.ok(aulas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AulaModel> buscarPorId(@PathVariable Long id) {
        return aulaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AulaModel> atualizarAula(@PathVariable Long id, @RequestBody AulaModel aula) {
        try {
            return ResponseEntity.ok(aulaService.atualizar(id, aula));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAula(@PathVariable Long id) {
        aulaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}