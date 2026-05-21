package br.edu.ifgoiano.clickdriveapi.service;

import br.edu.ifgoiano.clickdriveapi.dto.RegistroUsuarioDTO;
import br.edu.ifgoiano.clickdriveapi.model.AlunoModel;
import br.edu.ifgoiano.clickdriveapi.model.UsuarioModel;
import br.edu.ifgoiano.clickdriveapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    //injeção de dependencia do repositorio
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    //Metodo para criar ou atualiazar um usuario
    @Transactional
    public UsuarioModel salvar(RegistroUsuarioDTO dto){
        AlunoModel aluno = new AlunoModel();
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setSenha(dto.senha());
        aluno.setTelefone(dto.telefone());
        aluno.setCpf(dto.cpf());
        aluno.setDataNascimento(dto.dataNascimento());
        aluno.setCidade(dto.cidade());
        aluno.setCategoriaPretendida(dto.categoriaPretendida());

        return usuarioRepository.save(aluno);
    }
    //metodo para listar todos os usuarios cadastrados

    public List<UsuarioModel>listarTodos(){
        return usuarioRepository.findAll();
    }

    //Metodo para buscar um usuario especifico pelo ID
    public Optional<UsuarioModel> buscarPorId(Long id){
        return usuarioRepository.findById(id);

    }
    //Metodo para deletar um usuario

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
    //Metodo para atualizar o Usuario existente

    public UsuarioModel update(Long id, UsuarioModel usuarioAtualizado){

        //busca no banco de dados ou lança uma exceção se não achar
        UsuarioModel existente = usuarioRepository.findById(id).orElseThrow(()->new RuntimeException("usuario não encontrado"));



        //Atualizar os dados

        existente.setNome(usuarioAtualizado.getNome());
        existente.setEmail(usuarioAtualizado.getEmail());
        existente.setSenha(usuarioAtualizado.getSenha());
        existente.setTelefone(usuarioAtualizado.getTelefone());

        //salva por cima do banco
        return usuarioRepository.save(existente);



    }


}
