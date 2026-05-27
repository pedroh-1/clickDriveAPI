package br.edu.ifgoiano.clickdriveapi.controller;

import br.edu.ifgoiano.clickdriveapi.dto.DicaRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.DicaResponseDTO;
import br.edu.ifgoiano.clickdriveapi.service.DicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dicas")
public class DicaController {

    private final DicaService dicaService;

    public DicaController(DicaService dicaService) {
        this.dicaService = dicaService;
    }

    @PostMapping
    public ResponseEntity<?> criarDica(@RequestBody DicaRequestDTO dica) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(dicaService.salvarDica(dica));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<DicaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(dicaService.listarTodasDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DicaResponseDTO> buscarPorId(@PathVariable Long id) {
        return dicaService.buscarPorIdDTO(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<DicaResponseDTO>> listarPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(dicaService.listarPorCategoriaDTO(categoriaId));
    }
}
