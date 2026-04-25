package br.edu.ifgoiano.clickdriveapi.repository;

import br.edu.ifgoiano.clickdriveapi.model.QuestaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestaoRepository extends JpaRepository<QuestaoModel, Long> {

    List<QuestaoModel> findByCategoria(String categoria);

    // Busca questões aleatórias no banco de dados!
    @Query(value = "SELECT * FROM tb_questao ORDER BY RAND() LIMIT :quantidade", nativeQuery = true)
    List<QuestaoModel> buscarQuestoesAleatorias(int quantidade);

}
