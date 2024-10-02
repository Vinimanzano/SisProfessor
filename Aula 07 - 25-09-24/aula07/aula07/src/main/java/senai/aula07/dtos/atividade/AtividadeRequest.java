package senai.aula07.dtos.atividade;

import java.time.Instant;
import java.util.UUID;

public record AtividadeRequest(
        String descricao,
        UUID idProfessor,
        Long turma
) {
}
