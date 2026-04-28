package br.edu.ifgoiano.clickdriveapi.repository;

import br.edu.ifgoiano.clickdriveapi.model.DicaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DicaRepository extends JpaRepository<DicaModel, Long> {

    @Query("SELECT d FROM DicaModel d WHERE d.categoriaDica.id = :id")
    List<DicaModel> buscarDicasPorCategoria(@Param("id") Long id);
}