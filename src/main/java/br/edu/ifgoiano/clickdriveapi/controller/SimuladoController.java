package br.edu.ifgoiano.clickdriveapi.controller;

import br.edu.ifgoiano.clickdriveapi.dto.SimuladoResponseDTO;
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
    public ResponseEntity<SimuladoResponseDTO> gerarSimulado(@PathVariable Long alunoId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(simuladoService.gerarSimuladoDTO(alunoId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimuladoResponseDTO> verSimulado(@PathVariable Long id) {
        var simulado = simuladoService.buscarPorIdDTO(id);
        if (simulado != null) {
            return ResponseEntity.ok(simulado);
        }
        return ResponseEntity.notFound().build();
    }
}
