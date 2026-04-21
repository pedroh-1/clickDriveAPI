package br.edu.ifgoiano.clickdriveapi.repository;

import br.edu.ifgoiano.clickdriveapi.model.DisponibModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface DisponibRepository extends JpaRepository<DisponibModel, Long> {

    // Busca todas as disponibilidades de um instrutor específico
    List<DisponibModel> findByInstrutorId(Long instrutorId);

    // Busca disponibilidades por um dia da semana específico
    List<DisponibModel> findByDiasSemana(DayOfWeek diasSemana);

    // Busca apenas as disponibilidades que estão marcadas como ativas
    List<DisponibModel> findByAtivoTrue();
}