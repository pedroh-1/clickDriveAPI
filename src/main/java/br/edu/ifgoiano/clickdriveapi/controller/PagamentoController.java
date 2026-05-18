package br.edu.ifgoiano.clickdriveapi.controller;


import br.edu.ifgoiano.clickdriveapi.dto.PagamentoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.PagamentoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.mapper.PagamentoMapper;
import br.edu.ifgoiano.clickdriveapi.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private PagamentoMapper pagamentoMapper;

    // --- GERAR NOVA COBRANÇA ---
    @PostMapping
    public ResponseEntity<?> criarPagamento(@RequestBody PagamentoRequestDTO pagamento) {
        try {
            var novoPagamento = pagamentoService.gerarPagamento(pagamentoMapper.toEntity(pagamento));
            return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoMapper.toResponseDTO(novoPagamento));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // --- CONFIRMAR PAGAMENTO (Mudar de PENDENTE para PAGO) ---
    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<?> confirmarPagamento(@PathVariable Long id) {
        try {
            var pagamentoConfirmado = pagamentoService.confirmarPagamento(id);
            return ResponseEntity.ok(pagamentoMapper.toResponseDTO(pagamentoConfirmado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // --- LISTAR TODOS ---
    @GetMapping
    public ResponseEntity<List<PagamentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pagamentoService.listarTodos().stream()
                .map(pagamentoMapper::toResponseDTO)
                .toList());
    }

    // --- BUSCAR POR ID DA AULA ---
    @GetMapping("/aula/{aulaId}")
    public ResponseEntity<PagamentoResponseDTO> buscarPorAula(@PathVariable Long aulaId) {
        var pagamento = pagamentoService.buscarPorAula(aulaId);
        if (pagamento != null) {
            return ResponseEntity.ok(pagamentoMapper.toResponseDTO(pagamento));
        }
        return ResponseEntity.notFound().build();
    }

}
