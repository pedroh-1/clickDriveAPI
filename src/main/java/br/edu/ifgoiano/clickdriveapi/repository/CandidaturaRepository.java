package br.edu.ifgoiano.clickdriveapi.repository;


import br.edu.ifgoiano.clickdriveapi.model.CandidaturaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidaturaRepository extends JpaRepository<CandidaturaModel, Long> {


}
