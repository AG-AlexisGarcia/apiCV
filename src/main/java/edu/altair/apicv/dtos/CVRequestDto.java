package edu.altair.apicv.dtos;

public record CVRequestDto(
    Long alumnoId,
    String tituloProfesional,
    String resumen
) {}