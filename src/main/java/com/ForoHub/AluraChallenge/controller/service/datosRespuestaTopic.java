package com.ForoHub.AluraChallenge.controller.service;

import com.ForoHub.AluraChallenge.model.Topic;

import java.time.LocalDateTime;

// Record para obtener una respuesta personalizada
public record datosRespuestaTopic(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean status,
        String autor,
        String curso
) {
    public datosRespuestaTopic(Topic topic) {
        this(
                topic.getId(),
                topic.getTitulo(),
                topic.getMensaje(),
                topic.getFechaCreacion(),
                topic.getStatus(),
                topic.getAutor().getNombre(),
                topic.getCurso().getNombre()
        );
    }
}
