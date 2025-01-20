package com.ForoHub.AluraChallenge.controller.service;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record datosRegistroTopic(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        LocalDateTime fechaCreacion,
        @JsonAlias("status") Boolean status,
        @NotBlank
        String autor,
        @NotBlank
        String curso) {

}
