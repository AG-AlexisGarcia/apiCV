package edu.altair.apicv.dtos;

public record AptitudResponseDto(
    Long aptitudId,
    String nombre,
    String nivel
) {}