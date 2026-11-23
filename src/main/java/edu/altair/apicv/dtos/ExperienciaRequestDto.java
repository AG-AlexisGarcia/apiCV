package edu.altair.apicv.dtos;

import java.time.LocalDate;

public record ExperienciaRequestDto(
    String puesto,
    String empresa,
    LocalDate fechaInicio,
    LocalDate fechaFin,
    String descripcion
) {}
