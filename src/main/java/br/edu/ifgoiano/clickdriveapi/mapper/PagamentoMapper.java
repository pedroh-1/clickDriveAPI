package br.edu.ifgoiano.clickdriveapi.mapper;

import br.edu.ifgoiano.clickdriveapi.dto.PagamentoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.PagamentoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.model.PagamentoModel;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

    public PagamentoModel toEntity(PagamentoRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        PagamentoModel pagamento = new PagamentoModel();
        pagamento.setId(dto.id());
        pagamento.setValor(dto.valor());
        pagamento.setStatus(dto.status());
        pagamento.setDataPagamento(dto.dataPagamento());
        pagamento.setAulaId(dto.aulaId());
        return pagamento;
    }

    public PagamentoResponseDTO toResponseDTO(PagamentoModel entity) {
        if (entity == null) {
            return null;
        }

        return new PagamentoResponseDTO(
                entity.getId(),
                entity.getValor(),
                entity.getStatus(),
                entity.getDataPagamento(),
                entity.getAulaId()
        );
    }
}
