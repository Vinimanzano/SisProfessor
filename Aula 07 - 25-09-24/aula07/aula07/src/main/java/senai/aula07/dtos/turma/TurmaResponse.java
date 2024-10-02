package senai.aula07.dtos.turma;

import senai.aula07.entities.Atividade;
import senai.aula07.entities.Professor;
import senai.aula07.entities.Turma;

import java.util.Set;

public record TurmaResponse(
        Long id,
        Long numero,
        String nome,
        Professor professor,
        Set<Atividade> atividades
) {

    public TurmaResponse(Turma data) {
        this(
                data.getId(),
                data.getNumero(),
                data.getNome(),
                data.getProfessor(),
                data.getAtividades()
        );
    }
}
