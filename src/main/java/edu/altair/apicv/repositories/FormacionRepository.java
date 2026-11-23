package edu.altair.apicv.repositories;

import edu.altair.apicv.entities.Formacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FormacionRepository extends JpaRepository<Formacion, Long> {
    List<Formacion> findByCv_CvId(Long cvId);
    List<Formacion> findByCv_CvIdAndEsReglada(Long cvId, Boolean esReglada);
}