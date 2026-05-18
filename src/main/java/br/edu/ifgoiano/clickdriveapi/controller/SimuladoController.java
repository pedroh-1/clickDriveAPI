package br.edu.ifgoiano.clickdriveapi.controller;

import br.edu.ifgoiano.clickdriveapi.dto.SimuladoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.mapper.SimuladoMapper;
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

    @Autowired
    private SimuladoMapper simuladoMapper;

    @PostMapping("/gerar/{alunoId}")
    public ResponseEntity<SimuladoResponseDTO> gerarSimulado(@PathVariable Long alunoId) {
        try {
            var simuladoGerado = simuladoService.gerarSimulado(alunoId);
            return ResponseEntity.status(HttpStatus.CREATED).body(simuladoMapper.toResponseDTO(simuladoGerado));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimuladoResponseDTO> verSimulado(@PathVariable Long id) {
        var simulado = simuladoService.buscarPorId(id);
        if (simulado != null) {
            return ResponseEntity.ok(simuladoMapper.toResponseDTO(simulado));
        }
        return ResponseEntity.notFound().build();
    }
}
