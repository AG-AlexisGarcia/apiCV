package edu.altair.apicv.repositories;

import edu.altair.apicv.entities.Aptitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AptitudRepository extends JpaRepository<Aptitud, Long> {
    List<Aptitud> findByCv_CvId(Long cvId);
}