package senai.aula07.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import senai.aula07.dtos.professor.ProfessorRequest;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_professores")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Turma> turmas = new HashSet<>();

    public Professor(ProfessorRequest data) {
        setNome(data.nome());
        setEmail(data.email());
        setSenha(data.senha());
    }
}
