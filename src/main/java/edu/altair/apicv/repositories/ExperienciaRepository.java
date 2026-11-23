package edu.altair.apicv.repositories;

import edu.altair.apicv.entities.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Long> {
    List<Experiencia> findByCv_CvId(Long cvId);
}
