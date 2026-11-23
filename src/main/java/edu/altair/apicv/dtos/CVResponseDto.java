package edu.altair.apicv.dtos;

import java.util.List;

public record CVResponseDto(
    Long cvId,
    Long alumnoId,
    String nombreCompletoAlumno,
    String tituloProfesional,
    String resumen,
    List<ExperienciaResponseDto> experiencias,
    List<FormacionResponseDto> formaciones,
    List<AptitudResponseDto> aptitudes
) {}

