package br.edu.ifgoiano.clickdriveapi.service;

import br.edu.ifgoiano.clickdriveapi.model.CategoriaDicaModel;
import br.edu.ifgoiano.clickdriveapi.model.DicaModel;
import br.edu.ifgoiano.clickdriveapi.repository.CategoriaDicaRepository;
import br.edu.ifgoiano.clickdriveapi.repository.DicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
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


        CategoriaDicaModel categoria = categoriaDicaRepository.findById(dica.getCategoriaDica().getId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));

        dica.setCategoriaDica(categoria);
        dica.setDataPublicacao(LocalDateTime.now());

        return dicaRepository.save(dica);
    }

    // Listar todas as Dicas
    public List<DicaModel> listarTodas() {
        return dicaRepository.findAll();
    }

    public Optional<DicaModel> buscarPorId(Long id) {
        return dicaRepository.findById(id);
    }

    public List<DicaModel> listarPorCategoria(Long categoriaId) {
        return dicaRepository.buscarDicasPorCategoria(categoriaId);
    }
}
