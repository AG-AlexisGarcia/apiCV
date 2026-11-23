package edu.altair.apicv.config;

import edu.altair.apicv.entities.*;
import edu.altair.apicv.repositories.AlumnoRepository;
import edu.altair.apicv.repositories.CVRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AlumnoRepository alumnoRepository;
    private final CVRepository cvRepository;

    public DataInitializer(AlumnoRepository alumnoRepository, CVRepository cvRepository) {
        this.alumnoRepository = alumnoRepository;
        this.cvRepository = cvRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (alumnoRepository.count() > 0) {
            return; // Evita duplicar datos si la BD ya tiene registros
        }

        // 1. Crear Alumno
        Alumno alumno = new Alumno("Alexis", "García Elías", "alexis.garcia@altair.edu");
        alumnoRepository.save(alumno);

        // 2. Crear CV asociado al alumno con sus tres listas
        CV cv = new CV(
                "Desarrollador Web Full-Stack (2º DAW)",
                "Estudiante de Desarrollo de Aplicaciones Web enfocado en Spring Boot, TypeScript y HTML5/CSS3 accesible.",
                alumno
        );

        cv.addExperiencia(new Experiencia(
                "Desarrollador Web en Prácticas",
                "Deloitte",
                LocalDate.of(2026, 3, 16),
                LocalDate.of(2026, 6, 15),
                "Desarrollo de aplicaciones web en prácticas curriculares de 1º DAW.",
                cv
        ));

        cv.addFormacion(new Formacion("CFGS Desarrollo de Aplicaciones Web", "Colegio Altair", 2027, true, cv));
        cv.addFormacion(new Formacion("Curso de Accesibilidad Web WCAG 2.1", "Formación Complementaria", 2026, false, cv));

        cv.addAptitud(new Aptitud("Java / Spring Boot", "Avanzado", cv));
        cv.addAptitud(new Aptitud("HTML5 / CSS3", "Avanzado", cv));

        cvRepository.save(cv);
    }
}