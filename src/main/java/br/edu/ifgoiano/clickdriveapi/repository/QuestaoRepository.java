package br.edu.ifgoiano.clickdriveapi.repository;

import br.edu.ifgoiano.clickdriveapi.model.QuestaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestaoRepository extends JpaRepository<QuestaoModel, Long> {

    List<QuestaoModel> findByCategoria(String categoria);


}
