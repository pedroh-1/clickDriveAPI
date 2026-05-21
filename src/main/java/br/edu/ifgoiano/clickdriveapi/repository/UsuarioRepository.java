package br.edu.ifgoiano.clickdriveapi.repository;

import br.edu.ifgoiano.clickdriveapi.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    @Modifying
    @Query(value = "UPDATE usuario_model SET perfil = :perfil WHERE id = :id", nativeQuery = true)
    void atualizarPerfil(@Param("id") Long id, @Param("perfil") String perfil);

}
