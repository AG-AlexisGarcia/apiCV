package edu.altair.apicv.services;

import edu.altair.apicv.dtos.*;
import java.util.List;

public interface CVService {
    List<CVResponseDto> obtenerTodos();
    CVResponseDto obtenerPorId(Long cvId);
    CVResponseDto obtenerPorAlumnoId(Long alumnoId);
    CVResponseDto crear(CVRequestDto requestDto);
    CVResponseDto actualizar(Long cvId, CVRequestDto requestDto);
    void eliminar(Long cvId);

    CVResponseDto anadirExperiencia(Long cvId, ExperienciaRequestDto dto);
    void eliminarExperiencia(Long cvId, Long experienciaId);

    CVResponseDto anadirFormacion(Long cvId, FormacionRequestDto dto);
    void eliminarFormacion(Long cvId, Long formacionId);

    CVResponseDto anadirAptitud(Long cvId, AptitudRequestDto dto);
    void eliminarAptitud(Long cvId, Long aptitudId);
}