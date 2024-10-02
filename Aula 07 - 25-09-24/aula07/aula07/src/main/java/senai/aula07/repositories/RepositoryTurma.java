package senai.aula07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import senai.aula07.entities.Turma;

import java.util.List;
import java.util.UUID;

public interface RepositoryTurma extends JpaRepository<Turma, Long> {

    @Query("SELECT t FROM Turma t WHERE t.professor.id = :id")
    List<Turma> findByProfessor(@Param("id") UUID id);
}
