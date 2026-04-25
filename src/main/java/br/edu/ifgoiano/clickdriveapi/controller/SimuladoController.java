package br.edu.ifgoiano.clickdriveapi.controller;

import br.edu.ifgoiano.clickdriveapi.model.SimuladoModel;
import br.edu.ifgoiano.clickdriveapi.service.SimuladoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simulados")
public class SimuladoController {

    @Autowired
    private SimuladoService simuladoService;

    @PostMapping("/gerar/{alunoId}")
    public ResponseEntity<SimuladoModel> gerarSimulado(@PathVariable Long alunoId) {
        try {
            SimuladoModel simuladoGerado = simuladoService.gerarSimulado(alunoId);
            return ResponseEntity.status(HttpStatus.CREATED).body(simuladoGerado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimuladoModel> verSimulado(@PathVariable Long id) {
        SimuladoModel simulado = simuladoService.buscarPorId(id);
        if (simulado != null) {
            return ResponseEntity.ok(simulado);
        }
        return ResponseEntity.notFound().build();
    }
}
