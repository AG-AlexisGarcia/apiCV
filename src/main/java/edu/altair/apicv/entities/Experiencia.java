package edu.altair.apicv.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "experiencias", schema = "altaircv")
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experiencia_id")
    private Long experienciaId;

    @Column(name = "puesto", nullable = false, length = 100)
    private String puesto;

    @Column(name = "empresa", nullable = false, length = 100)
    private String empresa;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cv_id", nullable = false)
    private CV cv;

    public Experiencia() {}

    public Experiencia(String puesto, String empresa, LocalDate fechaInicio, LocalDate fechaFin, String descripcion, CV cv) {
        this.puesto = puesto;
        this.empresa = empresa;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
        this.cv = cv;
    }

    public Long getExperienciaId() { return experienciaId; }
    public void setExperienciaId(Long experienciaId) { this.experienciaId = experienciaId; }
    public String getPuesto() { return puesto; }
    public void setPuesto(String puesto) { this.puesto = puesto; }
    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public CV getCv() { return cv; }
    public void setCv(CV cv) { this.cv = cv; }
}
