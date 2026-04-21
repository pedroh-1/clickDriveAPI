package br.edu.ifgoiano.clickdriveapi.repository;


import br.edu.ifgoiano.clickdriveapi.model.InstrutorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrutorRepository  extends JpaRepository<InstrutorModel, Long > {
}
