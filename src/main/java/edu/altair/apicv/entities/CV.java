package edu.altair.apicv.entities;

import jakarta.persistence.*;


@Entity 
@Table (name = "cvs", schema = "altairCv" )

public class CV {

    @Column (name = "cv_id")
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long cvId;
    
    
    
}
