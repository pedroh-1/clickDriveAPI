package br.edu.ifgoiano.clickdriveapi.mapper;

import br.edu.ifgoiano.clickdriveapi.dto.AulaRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.AulaResponseDTO;
import br.edu.ifgoiano.clickdriveapi.model.AlunoModel;
import br.edu.ifgoiano.clickdriveapi.model.AulaModel;
import br.edu.ifgoiano.clickdriveapi.model.InstrutorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AulaMapper {

    @Mapping(target = "aluno", source = "alunoId")
    @Mapping(target = "instrutor", source = "instrutorId")
    AulaModel toEntity(AulaRequestDTO dto);

    @Mapping(target = "alunoId", source = "aluno.id")
    @Mapping(target = "instrutorId", source = "instrutor.id")
    AulaResponseDTO toResponseDTO(AulaModel entity);

    List<AulaResponseDTO> toResponseDTOList(List<AulaModel> entities);

    // Mapeamento manual para IDs
    default AlunoModel mapAluno(Long alunoId) {
        if (alunoId == null) return null;
        AlunoModel aluno = new AlunoModel();
        aluno.setId(alunoId);
        return aluno;
    }

    default InstrutorModel mapInstrutor(Long instrutorId) {
        if (instrutorId == null) return null;
        InstrutorModel instrutor = new InstrutorModel();
        instrutor.setId(instrutorId);
        return instrutor;
    }
}