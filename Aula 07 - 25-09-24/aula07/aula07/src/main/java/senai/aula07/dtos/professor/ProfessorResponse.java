package senai.aula07.dtos.professor;

import senai.aula07.entities.Professor;
import senai.aula07.entities.Turma;

import java.util.Set;
import java.util.UUID;

public record ProfessorResponse(
        UUID id,
        String nome,
        String email,
        String senha,
        Set<Turma> turmas
) {
    public ProfessorResponse(Professor data) {
        this(
                data.getId(),
                data.getNome(),
                data.getEmail(),
                data.getSenha(),
                data.getTurmas()
        );
    }
}
