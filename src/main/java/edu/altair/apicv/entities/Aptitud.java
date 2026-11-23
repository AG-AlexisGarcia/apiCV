package edu.altair.apicv.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "aptitudes", schema = "altaircv")
public class Aptitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aptitud_id")
    private Long aptitudId;

    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;

    @Column(name = "nivel", nullable = false, length = 40)
    private String nivel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cv_id", nullable = false)
    private CV cv;

    public Aptitud() {}

    public Aptitud(String nombre, String nivel, CV cv) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.cv = cv;
    }

    public Long getAptitudId() { return aptitudId; }
    public void setAptitudId(Long aptitudId) { this.aptitudId = aptitudId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }
    public CV getCv() { return cv; }
    public void setCv(CV cv) { this.cv = cv; }
}