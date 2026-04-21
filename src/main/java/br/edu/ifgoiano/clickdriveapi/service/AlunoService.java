package br.edu.ifgoiano.clickdriveapi.service;

import br.edu.ifgoiano.clickdriveapi.model.AlunoModel;
import br.edu.ifgoiano.clickdriveapi.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoModel salvar(AlunoModel aluno) {
        return alunoRepository.save(aluno);
    }

    public List<AlunoModel> listarTodos() {
        return alunoRepository.findAll();
    }

    public Optional<AlunoModel> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }

    public AlunoModel update(Long id, AlunoModel alunoAtualizado) {
        AlunoModel existente = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        existente.setCpf(alunoAtualizado.getCpf());
        existente.setDataNascimento(alunoAtualizado.getDataNascimento());
        existente.setCategoriaPretendida(alunoAtualizado.getCategoriaPretendida());

        return alunoRepository.save(existente);
    }
}