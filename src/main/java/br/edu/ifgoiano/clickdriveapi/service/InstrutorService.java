package br.edu.ifgoiano.clickdriveapi.service;



import br.edu.ifgoiano.clickdriveapi.model.AlunoModel;
import br.edu.ifgoiano.clickdriveapi.model.InstrutorModel;
import br.edu.ifgoiano.clickdriveapi.repository.InstrutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrutorService {

    private final InstrutorRepository instrutorRepository;

    public InstrutorService(InstrutorRepository instrutorRepository){
        this.instrutorRepository= instrutorRepository;

    }
    public InstrutorModel salvar(InstrutorModel instrutor){
        return instrutorRepository.save(instrutor);

    }

    public List<InstrutorModel>listaTodos(){
        return instrutorRepository.findAll();

    }

    public Optional<InstrutorModel> bucarPorId(Long id){
        return instrutorRepository.findById(id);


    }
    public void deletar(Long id){
        instrutorRepository.deleteById(id);
    }

    /*
    public InstrutorModel update( Long id, InstrutorModel instrutorAtualizado){

        InstrutorModel  existente = InstrutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        existente.setCpf(instrutorAtualizado.getCpf());
        existente.setDataNascimento(instrutorAtualizado.getDataNascimento());
        existente.setCategoriaPretendida(instrutorAtualizado.getCategoriaPretendida());

        return alunoRepository.save(existente);
    }
*/




}


