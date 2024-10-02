package senai.aula07.dtos.turma;

import java.util.UUID;

public record TurmaRequest(
        String nome,
        UUID professor
) {
}
