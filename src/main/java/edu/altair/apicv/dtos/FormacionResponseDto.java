package edu.altair.apicv.dtos;

public record FormacionResponseDto(
    Long formacionId,
    String titulo,
    String centro,
    Integer anioFin,
    Boolean esReglada
) {}