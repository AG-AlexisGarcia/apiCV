package edu.altair.apicv.repositories;

import edu.altair.apicv.entities.CV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CVRepository extends JpaRepository<CV, Long> {
    Optional<CV> findByAlumno_AlumnoId(Long alumnoId);
    boolean existsByAlumno_AlumnoId(Long alumnoId);
}