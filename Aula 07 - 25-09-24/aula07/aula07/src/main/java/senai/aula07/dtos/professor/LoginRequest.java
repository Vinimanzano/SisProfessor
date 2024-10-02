package senai.aula07.dtos.professor;

public record LoginRequest(
        String email,
        String senha
) {
}
