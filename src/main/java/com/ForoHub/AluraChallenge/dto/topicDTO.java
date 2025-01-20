package com.ForoHub.AluraChallenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record topicDTO(
        Long id,
        String titulo,
        String mensaje,
        Boolean status,
        // Como solo queremos el id, se mapea con Long
        Long autor,
        Long curso) {
}
