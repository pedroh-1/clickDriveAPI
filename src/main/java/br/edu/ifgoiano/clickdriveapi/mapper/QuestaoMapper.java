package br.edu.ifgoiano.clickdriveapi.mapper;

import br.edu.ifgoiano.clickdriveapi.dto.OpcaoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.OpcaoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.dto.QuestaoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.QuestaoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.model.OpcaoModel;
import br.edu.ifgoiano.clickdriveapi.model.QuestaoModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestaoMapper {

    private final OpcaoMapper opcaoMapper;

    public QuestaoMapper(OpcaoMapper opcaoMapper) {
        this.opcaoMapper = opcaoMapper;
    }

    public QuestaoModel toEntity(QuestaoRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        QuestaoModel questao = new QuestaoModel();
        questao.setId(dto.id());
        questao.setEnunciado(dto.enunciado());
        questao.setCategoria(dto.categoria());

        List<OpcaoModel> opcoes = toOpcaoEntities(dto.opcoes());
        opcoes.forEach(opcao -> opcao.setQuestao(questao));
        questao.setOpcoes(opcoes);

        return questao;
    }

    public QuestaoResponseDTO toResponseDTO(QuestaoModel entity) {
        if (entity == null) {
            return null;
        }

        List<OpcaoResponseDTO> opcoes = entity.getOpcoes() == null
                ? List.of()
                : entity.getOpcoes().stream()
                .map(opcaoMapper::toResponseDTO)
                .toList();

        return new QuestaoResponseDTO(
                entity.getId(),
                entity.getEnunciado(),
                entity.getCategoria(),
                opcoes
        );
    }

    private List<OpcaoModel> toOpcaoEntities(List<OpcaoRequestDTO> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        }

        return dtos.stream()
                .map(opcaoMapper::toEntity)
                .toList();
    }
}
