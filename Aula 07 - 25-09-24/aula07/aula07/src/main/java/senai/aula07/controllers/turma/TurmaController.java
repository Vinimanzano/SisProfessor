package senai.aula07.controllers.turma;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import senai.aula07.dtos.turma.TurmaRequest;
import senai.aula07.dtos.turma.TurmaResponse;
import senai.aula07.entities.Professor;
import senai.aula07.entities.Turma;
import senai.aula07.repositories.RepositoryProfessor;
import senai.aula07.repositories.RepositoryTurma;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TurmaController {

    private final RepositoryProfessor repositoryProfessor;
    private final RepositoryTurma repositoryTurma;

    public TurmaController(RepositoryProfessor repositoryProfessor,
                           RepositoryTurma repositoryTurma) {
        this.repositoryProfessor = repositoryProfessor;
        this.repositoryTurma = repositoryTurma;
    }

    @GetMapping("/turma")
    public ResponseEntity<List<TurmaResponse>> readAll() {
        List<TurmaResponse> listaTurma = repositoryTurma
                .findAll()
                .stream()
                .map(TurmaResponse::new)
                .toList();

        return ResponseEntity.ok(listaTurma);
    }

    @PostMapping("/turma")
    public ResponseEntity<Turma> create(@RequestBody @Validated
                                        TurmaRequest data) {
        var professor = repositoryProfessor.findById(data.professor());
        Integer numeroTurmas = professor.get().getTurmas().size();
        Long converterNumeroTurmas = numeroTurmas.longValue();

        var turma = new Turma(data);
        turma.setNumero(converterNumeroTurmas + 1);
        turma.setProfessor(professor.get());

        repositoryTurma.save(turma);

        return ResponseEntity.status(HttpStatus.CREATED).body(turma);
    }

    @PutMapping("/turma/{id}")
    public ResponseEntity<Object> updateNome(@PathVariable(value = "id") Long id,
                                             @RequestBody @Validated TurmaRequest data) {

        Optional<Turma> turma0 = repositoryTurma.findById(id);

        if (turma0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada!");
        }

        var turma = turma0.get();
        turma.setNome(data.nome());
        repositoryTurma.save(turma);

        return ResponseEntity.status(HttpStatus.OK).body(turma);
    }

    @DeleteMapping("/turma/{id_professor}/{id_turma}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id_professor")UUID idProfessor,
                                         @PathVariable(value = "id_turma")Long idTurma) {

        Optional<Turma> turma0 = repositoryTurma.findById(idTurma);
        Optional<Professor> professor0 = repositoryProfessor.findById(idProfessor);

        if (turma0.isEmpty() || professor0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma e/ou professor não encontrado!");
        }

        var turma = turma0.get();
        var professor = professor0.get();

        if (turma.getProfessor() != professor) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Turma não pode ser excluida!");
        }

        repositoryTurma.delete(turma);
        return ResponseEntity.status(HttpStatus.OK).body("Turma excluida com sucesso!");
    }

    @GetMapping("/turma/{id_professor}")
    public ResponseEntity<List<TurmaResponse>> readByProfessor(@PathVariable(value = "id_professor")UUID id) {
        List<TurmaResponse> listaTurma = repositoryTurma
                .findByProfessor(id)
                .stream()
                .map(TurmaResponse::new)
                .toList();

        return ResponseEntity.ok(listaTurma);
    }
}
