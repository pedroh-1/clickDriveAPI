package br.edu.ifgoiano.clickdriveapi.repository;

import br.edu.ifgoiano.clickdriveapi.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    // QUERY 1: Insere polimorficamente o registro na tabela filha dos instrutores salvando a integridade
    @Modifying
    @Query(value = "INSERT INTO instrutores (id, aprovado, valor_hora, credenciamento_detran, categoria) VALUES (:id, true, 0.0, :credenciamento, :categoria)", nativeQuery = true)
    void promoverParaInstrutor(@Param("id") Long id, @Param("credenciamento") String credenciamento, @Param("categoria") String categoria);

    // QUERY 2: Atualizada para apontar para a tabela nova 'usuarios'
    @Modifying
    @Query(value = "UPDATE usuarios SET tipo_perfil = :perfil WHERE id = :id", nativeQuery = true)
    void atualizarPerfil(@Param("id") Long id, @Param("perfil") String perfil);

}