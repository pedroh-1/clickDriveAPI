package br.edu.ifgoiano.clickdriveapi.service;

import br.edu.ifgoiano.clickdriveapi.model.InstrutorModel;
import br.edu.ifgoiano.clickdriveapi.repository.InstrutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrutorService {

    private final InstrutorRepository instrutorRepository;

    public InstrutorService(InstrutorRepository instrutorRepository){
        this.instrutorRepository = instrutorRepository;
    }

    public InstrutorModel salvar(InstrutorModel instrutor){
        return instrutorRepository.save(instrutor);
    }

    public List<InstrutorModel> listaTodos(){
        return instrutorRepository.findAll();
    }

    public Optional<InstrutorModel> buscarPorId(Long id){
        return instrutorRepository.findById(id);
    }

    public void deletar(Long id){
        instrutorRepository.deleteById(id);
    }

    public InstrutorModel update(Long id, InstrutorModel instrutorAtualizado) {
        // 1. Busca o instrutor existente no banco pelo ID
        InstrutorModel existente = instrutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instrutor não encontrado"));

        // 2. Atualiza os dados que vêm do UsuarioModel (Herança)
        existente.setNome(instrutorAtualizado.getNome());
        existente.setEmail(instrutorAtualizado.getEmail());
        existente.setTelefone(instrutorAtualizado.getTelefone());
        existente.setSenha(instrutorAtualizado.getSenha());

        // 3. Atualiza os dados específicos do InstrutorModel
        existente.setCredenciamentoDetran(instrutorAtualizado.getCredenciamentoDetran());
        existente.setCategoria(instrutorAtualizado.getCategoria());
        existente.setValorHora(instrutorAtualizado.getValorHora());
        existente.setAprovado(instrutorAtualizado.getAprovado());

        // 4. Salva as alterações
        return instrutorRepository.save(existente);
    }
}