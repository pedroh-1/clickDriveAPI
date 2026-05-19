package br.edu.ifgoiano.clickdriveapi.service;

import br.edu.ifgoiano.clickdriveapi.dto.AvaliacaoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.AvaliacaoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.mapper.AvaliacaoMapper;
import br.edu.ifgoiano.clickdriveapi.model.AvaliacaoModel;
import br.edu.ifgoiano.clickdriveapi.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AvaliacaoMapper avaliacaoMapper;

    public AvaliacaoResponseDTO salvarAvaliacao(AvaliacaoRequestDTO avaliacaoDTO) {
        AvaliacaoModel avaliacao = avaliacaoMapper.toEntity(avaliacaoDTO);
        AvaliacaoModel avaliacaoSalva = salvarAvaliacao(avaliacao);
        return avaliacaoMapper.toResponseDTO(avaliacaoSalva);
    }

    public AvaliacaoModel salvarAvaliacao(AvaliacaoModel avaliacao) {

        // Regra 1: A nota deve ter um limite lógico (ex: 1 a 5 estrelas)
        if (avaliacao.getNota() < 1 || avaliacao.getNota() > 5) {
            throw new IllegalArgumentException("A nota da avaliação deve estar entre 1 e 5.");
        }

        // Regra 2: Respeitar a cardinalidade (0..1) do diagrama
        AvaliacaoModel avaliacaoExistente = avaliacaoRepository.findByAulaId(avaliacao.getAulaId());
        if (avaliacaoExistente != null) {
            throw new IllegalStateException("Esta aula já foi avaliada e não pode receber outra avaliação.");
        }

        return avaliacaoRepository.save(avaliacao);
    }

    public AvaliacaoModel buscarPorAula(Long aulaId) {
        return avaliacaoRepository.findByAulaId(aulaId);
    }

    public AvaliacaoResponseDTO buscarPorAulaDTO(Long aulaId) {
        AvaliacaoModel avaliacao = buscarPorAula(aulaId);
        return avaliacao != null ? avaliacaoMapper.toResponseDTO(avaliacao) : null;
    }

    public List<AvaliacaoResponseDTO> listarTodasDTO() {
        return avaliacaoMapper.toResponseDTOList(avaliacaoRepository.findAll());
    }
}
