package br.edu.ifgoiano.clickdriveapi.controller;


import br.edu.ifgoiano.clickdriveapi.model.PagamentoModel;
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
    public ResponseEntity<?> criarPagamento(@RequestBody PagamentoModel pagamento) {
        try {
            PagamentoModel novoPagamento = pagamentoService.gerarPagamento(pagamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPagamento);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // --- CONFIRMAR PAGAMENTO (Mudar de PENDENTE para PAGO) ---
    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<?> confirmarPagamento(@PathVariable Long id) {
        try {
            PagamentoModel pagamentoConfirmado = pagamentoService.confirmarPagamento(id);
            return ResponseEntity.ok(pagamentoConfirmado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // --- LISTAR TODOS ---
    @GetMapping
    public ResponseEntity<List<PagamentoModel>> listarTodos() {
        return ResponseEntity.ok(pagamentoService.listarTodos());
    }

    // --- BUSCAR POR ID DA AULA ---
    @GetMapping("/aula/{aulaId}")
    public ResponseEntity<PagamentoModel> buscarPorAula(@PathVariable Long aulaId) {
        PagamentoModel pagamento = pagamentoService.buscarPorAula(aulaId);
        if (pagamento != null) {
            return ResponseEntity.ok(pagamento);
        }
        return ResponseEntity.notFound().build();
    }

}
