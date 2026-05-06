package br.edu.ifgoiano.clickdriveapi.service;

import br.edu.ifgoiano.clickdriveapi.model.PagamentoModel;
import br.edu.ifgoiano.clickdriveapi.model.StatusPagamento;
import br.edu.ifgoiano.clickdriveapi.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    // --- REGRA 1: GERAR A COBRANÇA ---
    public PagamentoModel gerarPagamento(PagamentoModel pagamento) {


        if (pagamento.getValor() == null || pagamento.getValor() <= 0) {
            throw new IllegalArgumentException("O valor do pagamento deve ser maior que zero.");
        }


        if (pagamentoRepository.findByAulaId(pagamento.getAulaId()).isPresent()) {
            throw new IllegalStateException("Já existe uma cobrança gerada para esta aula.");
        }


        pagamento.setStatus(StatusPagamento.PENDENTE);
        pagamento.setDataPagamento(null);

        return pagamentoRepository.save(pagamento);
    }

    //  CONFIRMAR RECEBIMENTO
    public PagamentoModel confirmarPagamento(Long id) {
        PagamentoModel pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado."));

        if (StatusPagamento.PAGO.equals(pagamento.getStatus())) {
            throw new IllegalStateException("Este pagamento já foi confirmado anteriormente.");
        }

        pagamento.setStatus(StatusPagamento.PAGO);
        pagamento.setDataPagamento(LocalDateTime.now());

        return pagamentoRepository.save(pagamento);
    }

    // --- BUSCAS ---
    public List<PagamentoModel> listarTodos() {
        return pagamentoRepository.findAll();
    }

    public PagamentoModel buscarPorAula(Long aulaId) {
        return pagamentoRepository.findByAulaId(aulaId).orElse(null);
    }

}
