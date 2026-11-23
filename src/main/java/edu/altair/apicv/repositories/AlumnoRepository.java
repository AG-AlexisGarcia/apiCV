package edu.altair.apicv.repositories;

import edu.altair.apicv.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    boolean existsByEmail(String email);
}
