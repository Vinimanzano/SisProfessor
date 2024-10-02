package senai.aula07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senai.aula07.entities.Professor;

import java.util.Optional;
import java.util.UUID;

public interface RepositoryProfessor extends JpaRepository<Professor, UUID> {

    Optional<Professor> findByEmail(String email);
}
