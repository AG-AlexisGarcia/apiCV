package edu.altair.apicv.services.impl;

import edu.altair.apicv.dtos.*;
import edu.altair.apicv.entities.*;
import edu.altair.apicv.exceptions.CVAlreadyExistsException;
import edu.altair.apicv.exceptions.RecursoNotFoundException;
import edu.altair.apicv.repositories.*;
import edu.altair.apicv.services.CVService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CVServiceImpl implements CVService {

    private final CVRepository cvRepository;
    private final AlumnoRepository alumnoRepository;
    private final ExperienciaRepository experienciaRepository;
    private final FormacionRepository formacionRepository;
    private final AptitudRepository aptitudRepository;

    public CVServiceImpl(CVRepository cvRepository,
                         AlumnoRepository alumnoRepository,
                         ExperienciaRepository experienciaRepository,
                         FormacionRepository formacionRepository,
                         AptitudRepository aptitudRepository) {
        this.cvRepository = cvRepository;
        this.alumnoRepository = alumnoRepository;
        this.experienciaRepository = experienciaRepository;
        this.formacionRepository = formacionRepository;
        this.aptitudRepository = aptitudRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CVResponseDto> obtenerTodos() {
        return cvRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CVResponseDto obtenerPorId(Long cvId) {
        CV cv = cvRepository.findById(cvId)
                .orElseThrow(() -> new RecursoNotFoundException("CV no encontrado con el ID: " + cvId));
        return mapToResponseDto(cv);
    }

    @Override
    @Transactional(readOnly = true)
    public CVResponseDto obtenerPorAlumnoId(Long alumnoId) {
        CV cv = cvRepository.findByAlumno_AlumnoId(alumnoId)
                .orElseThrow(() -> new RecursoNotFoundException("No existe CV para el alumno con ID: " + alumnoId));
        return mapToResponseDto(cv);
    }

    @Override
    @Transactional
    public CVResponseDto crear(CVRequestDto dto) {
        Alumno alumno = alumnoRepository.findById(dto.alumnoId())
                .orElseThrow(() -> new RecursoNotFoundException("Alumno no encontrado con el ID: " + dto.alumnoId()));

        if (cvRepository.existsByAlumno_AlumnoId(dto.alumnoId())) {
            throw new CVAlreadyExistsException("El alumno con ID " + dto.alumnoId() + " ya tiene un CV registrado.");
        }

        CV nuevoCv = new CV(dto.tituloProfesional(), dto.resumen(), alumno);
        CV guardado = cvRepository.save(nuevoCv);
        return mapToResponseDto(guardado);
    }

    @Override
    @Transactional
    public CVResponseDto actualizar(Long cvId, CVRequestDto dto) {
        CV cvExistente = cvRepository.findById(cvId)
                .orElseThrow(() -> new RecursoNotFoundException("CV no encontrado con el ID: " + cvId));

        cvExistente.setTituloProfesional(dto.tituloProfesional());
        cvExistente.setResumen(dto.resumen());

        CV actualizado = cvRepository.save(cvExistente);
        return mapToResponseDto(actualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long cvId) {
        if (!cvRepository.existsById(cvId)) {
            throw new RecursoNotFoundException("Imposible eliminar. CV no encontrado con el ID: " + cvId);
        }
        cvRepository.deleteById(cvId);
    }

    @Override
    @Transactional
    public CVResponseDto anadirExperiencia(Long cvId, ExperienciaRequestDto dto) {
        CV cv = cvRepository.findById(cvId)
                .orElseThrow(() -> new RecursoNotFoundException("CV no encontrado con el ID: " + cvId));

        Experiencia exp = new Experiencia(
                dto.puesto(),
                dto.empresa(),
                dto.fechaInicio(),
                dto.fechaFin(),
                dto.descripcion(),
                cv
        );
        cv.addExperiencia(exp);
        return mapToResponseDto(cvRepository.save(cv));
    }

    @Override
    @Transactional
    public void eliminarExperiencia(Long cvId, Long experienciaId) {
        CV cv = cvRepository.findById(cvId)
                .orElseThrow(() -> new RecursoNotFoundException("CV no encontrado con el ID: " + cvId));
        Experiencia exp = experienciaRepository.findById(experienciaId)
                .orElseThrow(() -> new RecursoNotFoundException("Experiencia no encontrada con el ID: " + experienciaId));

        cv.removeExperiencia(exp);
        cvRepository.save(cv);
    }

    @Override
    @Transactional
    public CVResponseDto anadirFormacion(Long cvId, FormacionRequestDto dto) {
        CV cv = cvRepository.findById(cvId)
                .orElseThrow(() -> new RecursoNotFoundException("CV no encontrado con el ID: " + cvId));

        Formacion form = new Formacion(
                dto.titulo(),
                dto.centro(),
                dto.anioFin(),
                dto.esReglada(),
                cv
        );
        cv.addFormacion(form);
        return mapToResponseDto(cvRepository.save(cv));
    }

    @Override
    @Transactional
    public void eliminarFormacion(Long cvId, Long formacionId) {
        CV cv = cvRepository.findById(cvId)
                .orElseThrow(() -> new RecursoNotFoundException("CV no encontrado con el ID: " + cvId));
        Formacion form = formacionRepository.findById(formacionId)
                .orElseThrow(() -> new RecursoNotFoundException("Formación no encontrada con el ID: " + formacionId));

        cv.removeFormacion(form);
        cvRepository.save(cv);
    }

    @Override
    @Transactional
    public CVResponseDto anadirAptitud(Long cvId, AptitudRequestDto dto) {
        CV cv = cvRepository.findById(cvId)
                .orElseThrow(() -> new RecursoNotFoundException("CV no encontrado con el ID: " + cvId));

        Aptitud apt = new Aptitud(dto.nombre(), dto.nivel(), cv);
        cv.addAptitud(apt);
        return mapToResponseDto(cvRepository.save(cv));
    }

    @Override
    @Transactional
    public void eliminarAptitud(Long cvId, Long aptitudId) {
        CV cv = cvRepository.findById(cvId)
                .orElseThrow(() -> new RecursoNotFoundException("CV no encontrado con el ID: " + cvId));
        Aptitud apt = aptitudRepository.findById(aptitudId)
                .orElseThrow(() -> new RecursoNotFoundException("Aptitud no encontrada con el ID: " + aptitudId));

        cv.removeAptitud(apt);
        cvRepository.save(cv);
    }

    private CVResponseDto mapToResponseDto(CV cv) {
        List<ExperienciaResponseDto> experiencias = cv.getExperiencias().stream()
                .map(e -> new ExperienciaResponseDto(
                        e.getExperienciaId(),
                        e.getPuesto(),
                        e.getEmpresa(),
                        e.getFechaInicio(),
                        e.getFechaFin(),
                        e.getDescripcion()
                ))
                .toList();

        List<FormacionResponseDto> formaciones = cv.getFormaciones().stream()
                .map(f -> new FormacionResponseDto(
                        f.getFormacionId(),
                        f.getTitulo(),
                        f.getCentro(),
                        f.getAnioFin(),
                        f.getEsReglada()
                ))
                .toList();

        List<AptitudResponseDto> aptitudes = cv.getAptitudes().stream()
                .map(a -> new AptitudResponseDto(
                        a.getAptitudId(),
                        a.getNombre(),
                        a.getNivel()
                ))
                .toList();

        String nombreCompleto = cv.getAlumno().getNombre() + " " + cv.getAlumno().getApellidos();

        return new CVResponseDto(
                cv.getCvId(),
                cv.getAlumno().getAlumnoId(),
                nombreCompleto,
                cv.getTituloProfesional(),
                cv.getResumen(),
                experiencias,
                formaciones,
                aptitudes
        );
    }
}