package edu.altair.apicv.dtos;

public record FormacionRequestDto(
    String titulo,
    String centro,
    Integer anioFin,
    Boolean esReglada
) {}