package senai.aula07.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import senai.aula07.dtos.atividade.AtividadeRequest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "tb_atividades")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private Instant dataInicio;

    private Instant dataFim;
    private UUID idProfessor;

    @ManyToOne
    @JsonIgnore
    private Turma turma;

    public Atividade(AtividadeRequest data) {
        setDescricao(data.descricao());
        setIdProfessor(data.idProfessor());
        setDataInicio(Instant.now());
        setDataFim(getDataInicio().plus(7, ChronoUnit.DAYS));
    }
}
