package br.edu.ifgoiano.clickdriveapi.repository;


import br.edu.ifgoiano.clickdriveapi.model.SimuladoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimuladoRepository extends JpaRepository<SimuladoModel, Long> {
 List<SimuladoModel> findByAlunoId(Long alunoId);


}
