package com.ForoHub.AluraChallenge.model;

import com.ForoHub.AluraChallenge.controller.service.datosRegistroTopic;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(nullable = false)
    private Boolean status;

    // Se pone en la llave que relaciona las tablas
    // Se usa el lado de Many(topics) para indicar su One(autor_id)
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    // En estas relaciones se mapea con las entidades completas
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "curso_id")
    private Curso curso;

    // Requisito técnico para que Hibernate pueda trabajar en la persistencia y conversión de entidades
    public Topic(){}

    //Constructor creado para registrar datos de los Topics
    public Topic(datosRegistroTopic datosRegistroTopic) {
    }

    private LocalDateTime fechaCreacion;

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


    public Topic(String titulo, String mensaje, LocalDateTime fechaCreacion, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.autor = autor;
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
