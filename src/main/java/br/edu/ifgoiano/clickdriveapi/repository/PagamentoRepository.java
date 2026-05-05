package br.edu.ifgoiano.clickdriveapi.repository;

import br.edu.ifgoiano.clickdriveapi.model.PagamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagamentoRepository extends JpaRepository<PagamentoModel, Long> {

    Optional<PagamentoModel> findByAulaId(Long aulaId);


}
