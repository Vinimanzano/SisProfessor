package senai.aula07.controllers.atividade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import senai.aula07.dtos.atividade.AtividadeRequest;
import senai.aula07.dtos.atividade.AtividadeResponse;
import senai.aula07.entities.Atividade;
import senai.aula07.repositories.RepositoryAtividade;
import senai.aula07.repositories.RepositoryTurma;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AtividadeController {

    private final RepositoryAtividade repositoryAtividade;
    private final RepositoryTurma repositoryTurma;

    public AtividadeController(RepositoryAtividade repositoryAtividade,
                               RepositoryTurma repositoryTurma) {
        this.repositoryAtividade = repositoryAtividade;
        this.repositoryTurma = repositoryTurma;
    }

    @GetMapping("/atividade")
    public ResponseEntity<List<AtividadeResponse>> readALl() {
        List<AtividadeResponse> listaAtividade = repositoryAtividade
                .findAll()
                .stream()
                .map(AtividadeResponse::new)
                .toList();

        return ResponseEntity.ok(listaAtividade);
    }

    @PostMapping("/atividade")
    public ResponseEntity<Atividade> create(@RequestBody @Validated AtividadeRequest data) {
        var atividade = new Atividade(data);
        var turma = repositoryTurma.findById(data.turma());

        atividade.setTurma(turma.get());

        repositoryAtividade.save(atividade);

        return ResponseEntity.status(HttpStatus.CREATED).body(atividade);
    }

    @DeleteMapping("/atividade/{id_atividade}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id_atividade")Long idAtividade) {
        System.out.println(idAtividade);
        Optional<Atividade> atividade0 = repositoryAtividade.findById(idAtividade);

        if (atividade0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atividade não encontrada!");
        }

        repositoryAtividade.deleteById(idAtividade);
        return ResponseEntity.status(HttpStatus.OK).body("Atividade excluida com sucesso!");
    }

    @GetMapping("/atividade/turma/{id_turma}")
    public ResponseEntity<List<AtividadeResponse>> readByTurma(@PathVariable(value = "id_turma") Long id) {
        List<AtividadeResponse> listaAtividade = repositoryAtividade
                .findByTurma(id)
                .stream()
                .map(AtividadeResponse::new)
                .toList();

        return ResponseEntity.ok(listaAtividade);
    }
}
