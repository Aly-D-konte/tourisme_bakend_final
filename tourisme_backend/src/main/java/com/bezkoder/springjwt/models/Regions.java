package com.bezkoder.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Regions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_regions;
    private String nomregions;
    @Column(unique = true)
    private String coderegion;
    private String activiterregion;
    private String superficieregion;
    private String languemregion;
    private String images;
    private String description;
    private int nombrecommentaire;


    @JoinColumn(name = "id_pays") // ICI IMPORTANT
    @ManyToOne
    private Pays pays;




}
