package com.ForoHub.AluraChallenge.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    // Lado del One(cursos)
    @OneToMany(mappedBy = "curso")
    @JsonBackReference
    // Lista para contener los elementos de topics
    // Aquí porque un curso puede tener una lista(muchos) topics
    private List<Topic> topics;

    public Curso(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
