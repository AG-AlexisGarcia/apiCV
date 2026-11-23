package edu.altair.apicv.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "formaciones", schema = "altaircv")
public class Formacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "formacion_id")
    private Long formacionId;

    @Column(name = "titulo", nullable = false, length = 120)
    private String titulo;

    @Column(name = "centro", nullable = false, length = 120)
    private String centro;

    @Column(name = "anio_fin")
    private Integer anioFin;

    @Column(name = "es_reglada", nullable = false)
    private Boolean esReglada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cv_id", nullable = false)
    private CV cv;

    public Formacion() {}

    public Formacion(String titulo, String centro, Integer anioFin, Boolean esReglada, CV cv) {
        this.titulo = titulo;
        this.centro = centro;
        this.anioFin = anioFin;
        this.esReglada = esReglada;
        this.cv = cv;
    }

    public Long getFormacionId() { return formacionId; }
    public void setFormacionId(Long formacionId) { this.formacionId = formacionId; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getCentro() { return centro; }
    public void setCentro(String centro) { this.centro = centro; }
    public Integer getAnioFin() { return anioFin; }
    public void setAnioFin(Integer anioFin) { this.anioFin = anioFin; }
    public Boolean getEsReglada() { return esReglada; }
    public void setEsReglada(Boolean esReglada) { this.esReglada = esReglada; }
    public CV getCv() { return cv; }
    public void setCv(CV cv) { this.cv = cv; }
}
