package br.edu.ifgoiano.clickdriveapi.repository;


import br.edu.ifgoiano.clickdriveapi.model.CandidaturaModel;
import br.edu.ifgoiano.clickdriveapi.model.StatusCandidatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidaturaRepository extends JpaRepository<CandidaturaModel, Long> {
        // O parâmetro DEVE ser StatusCandidatura e não String
        List<CandidaturaModel> findByStatus(StatusCandidatura status);
    }



