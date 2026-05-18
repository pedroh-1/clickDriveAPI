package br.edu.ifgoiano.clickdriveapi.controller;

import br.edu.ifgoiano.clickdriveapi.dto.DicaRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.DicaResponseDTO;
import br.edu.ifgoiano.clickdriveapi.mapper.DicaMapper;
import br.edu.ifgoiano.clickdriveapi.service.DicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dicas")
public class DicaController {

    private final DicaService dicaService;
    private final DicaMapper dicaMapper;

    public DicaController(DicaService dicaService, DicaMapper dicaMapper) {
        this.dicaService = dicaService;
        this.dicaMapper = dicaMapper;
    }

    @PostMapping
    public ResponseEntity<?> criarDica(@RequestBody DicaRequestDTO dica) {
        try {
            var dicaSalva = dicaService.salvarDica(dicaMapper.toEntity(dica));
            return ResponseEntity.status(HttpStatus.CREATED).body(dicaMapper.toResponseDTO(dicaSalva));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<DicaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(dicaService.listarTodas().stream()
                .map(dicaMapper::toResponseDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DicaResponseDTO> buscarPorId(@PathVariable Long id) {
        return dicaService.buscarPorId(id)
                .map(dicaMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<DicaResponseDTO>> listarPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(dicaService.listarPorCategoria(categoriaId).stream()
                .map(dicaMapper::toResponseDTO)
                .toList());
    }
}
