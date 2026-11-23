package edu.altair.apicv.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cvs", schema = "altaircv")
public class CV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cv_id")
    private Long cvId;

    @Column(name = "titulo_profesional", nullable = false, length = 120)
    private String tituloProfesional;

    @Column(name = "resumen", length = 500)
    private String resumen;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "alumno_id",
        referencedColumnName = "alumno_id",
        nullable = false,
        unique = true,
        foreignKey = @ForeignKey(name = "fk_cvs_alumno")
    )
    private Alumno alumno;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Experiencia> experiencias = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Formacion> formaciones = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Aptitud> aptitudes = new ArrayList<>();

    public CV() {}

    public CV(String tituloProfesional, String resumen, Alumno alumno) {
        this.tituloProfesional = tituloProfesional;
        this.resumen = resumen;
        this.alumno = alumno;
    }

    public void addExperiencia(Experiencia exp) {
        experiencias.add(exp);
        exp.setCv(this);
    }

    public void removeExperiencia(Experiencia exp) {
        experiencias.remove(exp);
        exp.setCv(null);
    }

    public void addFormacion(Formacion form) {
        formaciones.add(form);
        form.setCv(this);
    }

    public void removeFormacion(Formacion form) {
        formaciones.remove(form);
        form.setCv(null);
    }

    public void addAptitud(Aptitud apt) {
        aptitudes.add(apt);
        apt.setCv(this);
    }

    public void removeAptitud(Aptitud apt) {
        aptitudes.remove(apt);
        apt.setCv(null);
    }

    public Long getCvId() { return cvId; }
    public void setCvId(Long cvId) { this.cvId = cvId; }
    public String getTituloProfesional() { return tituloProfesional; }
    public void setTituloProfesional(String tituloProfesional) { this.tituloProfesional = tituloProfesional; }
    public String getResumen() { return resumen; }
    public void setResumen(String resumen) { this.resumen = resumen; }
    public Alumno getAlumno() { return alumno; }
    public void setAlumno(Alumno alumno) { this.alumno = alumno; }
    public List<Experiencia> getExperiencias() { return experiencias; }
    public void setExperiencias(List<Experiencia> experiencias) { this.experiencias = experiencias; }
    public List<Formacion> getFormaciones() { return formaciones; }
    public void setFormaciones(List<Formacion> formaciones) { this.formaciones = formaciones; }
    public List<Aptitud> getAptitudes() { return aptitudes; }
    public void setAptitudes(List<Aptitud> aptitudes) { this.aptitudes = aptitudes; }
}