package br.edu.ifgoiano.clickdriveapi.controller;

import br.edu.ifgoiano.clickdriveapi.model.DisponibModel;
import br.edu.ifgoiano.clickdriveapi.service.DisponibService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disponibilidades")
public class DisponibController {

    private final DisponibService disponibService;

    public DisponibController(DisponibService disponibService) {
        this.disponibService = disponibService;
    }

    @PostMapping
    public ResponseEntity<DisponibModel> criar(@RequestBody DisponibModel disponibilidade) {
        DisponibModel novaDisponibilidade = disponibService.salvar(disponibilidade);
        return new ResponseEntity<>(novaDisponibilidade, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DisponibModel>> listarTodas() {
        return ResponseEntity.ok(disponibService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisponibModel> buscarPorId(@PathVariable Long id) {
        return disponibService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisponibModel> atualizar(@PathVariable Long id, @RequestBody DisponibModel disponibilidade) {
        try {
            return ResponseEntity.ok(disponibService.atualizar(id, disponibilidade));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        disponibService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}