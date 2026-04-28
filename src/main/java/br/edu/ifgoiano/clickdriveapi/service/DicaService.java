package br.edu.ifgoiano.clickdriveapi.service;

import br.edu.ifgoiano.clickdriveapi.model.CategoriaDicaModel;
import br.edu.ifgoiano.clickdriveapi.model.DicaModel;
import br.edu.ifgoiano.clickdriveapi.repository.CategoriaDicaRepository;
import br.edu.ifgoiano.clickdriveapi.repository.DicaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class DicaService {

    @Autowired
    private DicaRepository dicaRepository;

    @Autowired
    private CategoriaDicaRepository categoriaDicaRepository;


    public CategoriaDicaModel salvarCategoria(CategoriaDicaModel categoria){
        return categoriaDicaRepository.save(categoria);
    }

    public DicaModel salvarDica(DicaModel dica) {

        if (dica.getCategoriaDica() == null || dica.getCategoriaDica().getId() == null) {
            throw new IllegalArgumentException("A dica precisa estar vinculada a uma categoria válida.");
        }


        categoriaDicaRepository.findById(dica.getCategoriaDica().getId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));


        dica.setDataPublicacao(LocalDateTime.now());

        return dicaRepository.save(dica);
    }

    // Listar todas as Dicas
    public List<DicaModel> listarTodas() {
        return dicaRepository.findAll();
    }
}
