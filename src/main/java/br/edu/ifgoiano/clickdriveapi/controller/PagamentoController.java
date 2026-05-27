package br.edu.ifgoiano.clickdriveapi.controller;


import br.edu.ifgoiano.clickdriveapi.dto.PagamentoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.PagamentoResponseDTO;
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

    // --- GERAR NOVA COBRANÇA ---
    @PostMapping
    public ResponseEntity<?> criarPagamento(@RequestBody PagamentoRequestDTO pagamento) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.gerarPagamento(pagamento));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // --- CONFIRMAR PAGAMENTO (Mudar de PENDENTE para PAGO) ---
    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<?> confirmarPagamento(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pagamentoService.confirmarPagamentoDTO(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // --- LISTAR TODOS ---
    @GetMapping
    public ResponseEntity<List<PagamentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pagamentoService.listarTodosDTO());
    }

    // --- BUSCAR POR ID DA AULA ---
    @GetMapping("/aula/{aulaId}")
    public ResponseEntity<PagamentoResponseDTO> buscarPorAula(@PathVariable Long aulaId) {
        var pagamento = pagamentoService.buscarPorAulaDTO(aulaId);
        if (pagamento != null) {
            return ResponseEntity.ok(pagamento);
        }
        return ResponseEntity.notFound().build();
    }

}
