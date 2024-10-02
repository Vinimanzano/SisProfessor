package senai.aula07.dtos.professor;

public record ProfessorRequest(
        String nome,
        String email,
        String senha
) {
}
