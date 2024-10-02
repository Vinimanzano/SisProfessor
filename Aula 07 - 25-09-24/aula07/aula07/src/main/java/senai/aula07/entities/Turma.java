package senai.aula07.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import senai.aula07.dtos.turma.TurmaRequest;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_turmas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numero;
    private String nome;

    @ManyToOne
    @JsonIgnore
    private Professor professor;

    @OneToMany(mappedBy = "turma", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Atividade> atividades = new HashSet<>();

    public Turma(TurmaRequest data) {
        setNome(data.nome());
    }
}
