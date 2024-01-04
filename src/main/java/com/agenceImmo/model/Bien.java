package com.agenceImmo.model;
//    Un bien (id, nom, surface, prix, prix charges, adresse, code postale, ville, telephone, description, mini description, une photo)

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "surface")
    private float surface;

    @Column(name = "prix")
    private float prix;

    @Column(name = "charge")
    private float charge;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "cp")
    private int cp;

    @Column(name = "ville")
    private String ville;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "description")
    private String description;

    @Column(name = "mini_description")
    private String miniDescription;

    @Column(name = "photo")
    private String photo;

    @Column(name = "active", columnDefinition = "boolean default 0")
    private boolean active;




}
