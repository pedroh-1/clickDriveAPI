package br.edu.ifgoiano.clickdriveapi.repository;

import br.edu.ifgoiano.clickdriveapi.model.AulaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AulaRepository extends JpaRepository<AulaModel, Long> {

    // Método útil para buscar aulas por uma data específica
    List<AulaModel> findByData(LocalDate data);

    // Método útil para buscar aulas por categoria de veículo
    List<AulaModel> findByCategoriaVeiculo(String categoriaVeiculo);
}