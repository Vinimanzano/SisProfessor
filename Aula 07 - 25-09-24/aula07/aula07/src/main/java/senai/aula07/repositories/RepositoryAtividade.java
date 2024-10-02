package senai.aula07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import senai.aula07.entities.Atividade;

import java.util.List;

public interface RepositoryAtividade extends JpaRepository<Atividade, Long> {
    @Query("SELECT a FROM Atividade a WHERE a.turma.id = :id")
    List<Atividade> findByTurma(@Param("id") Long id);
}
