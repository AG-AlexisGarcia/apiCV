package edu.altair.apicv.controllers;

import edu.altair.apicv.dtos.*;
import edu.altair.apicv.services.CVService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cvs")
public class CVController {

    private final CVService cvService;

    public CVController(CVService cvService) {
        this.cvService = cvService;
    }

    // SELECT - Listar todos los CVs (List en lugar de Page)
    @GetMapping
    public ResponseEntity<List<CVResponseDto>> obtenerTodos() {
        return ResponseEntity.ok(cvService.obtenerTodos());
    }

    // SELECT - CV completo por ID de CV
    @GetMapping("/{id}")
    public ResponseEntity<CVResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cvService.obtenerPorId(id));
    }

    // SELECT - CV completo de un alumno (incluye listas de experiencias, formaciones y aptitudes)
    @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<CVResponseDto> obtenerPorAlumno(@PathVariable Long alumnoId) {
        return ResponseEntity.ok(cvService.obtenerPorAlumnoId(alumnoId));
    }

    // INSERT - Crear CV
    @PostMapping
    public ResponseEntity<CVResponseDto> crear(@RequestBody CVRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cvService.crear(requestDto));
    }

    // UPDATE - Actualizar cabecera del CV
    @PutMapping("/{id}")
    public ResponseEntity<CVResponseDto> actualizar(@PathVariable Long id, @RequestBody CVRequestDto requestDto) {
        return ResponseEntity.ok(cvService.actualizar(id, requestDto));
    }

    // DELETE - Eliminar CV
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cvService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Ramas del CV: Experiencias, Formaciones y Aptitudes
    @PostMapping("/{cvId}/experiencias")
    public ResponseEntity<CVResponseDto> anadirExperiencia(
            @PathVariable Long cvId,
            @RequestBody ExperienciaRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cvService.anadirExperiencia(cvId, dto));
    }

    @DeleteMapping("/{cvId}/experiencias/{expId}")
    public ResponseEntity<Void> eliminarExperiencia(@PathVariable Long cvId, @PathVariable Long expId) {
        cvService.eliminarExperiencia(cvId, expId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cvId}/formaciones")
    public ResponseEntity<CVResponseDto> anadirFormacion(
            @PathVariable Long cvId,
            @RequestBody FormacionRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cvService.anadirFormacion(cvId, dto));
    }

    @DeleteMapping("/{cvId}/formaciones/{formId}")
    public ResponseEntity<Void> eliminarFormacion(@PathVariable Long cvId, @PathVariable Long formId) {
        cvService.eliminarFormacion(cvId, formId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cvId}/aptitudes")
    public ResponseEntity<CVResponseDto> anadirAptitud(
            @PathVariable Long cvId,
            @RequestBody AptitudRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cvService.anadirAptitud(cvId, dto));
    }

    @DeleteMapping("/{cvId}/aptitudes/{aptId}")
    public ResponseEntity<Void> eliminarAptitud(@PathVariable Long cvId, @PathVariable Long aptId) {
        cvService.eliminarAptitud(cvId, aptId);
        return ResponseEntity.noContent().build();
    }
}