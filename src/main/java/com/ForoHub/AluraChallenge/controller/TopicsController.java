package com.ForoHub.AluraChallenge.controller;

import com.ForoHub.AluraChallenge.controller.service.datosRegistroTopic;
import com.ForoHub.AluraChallenge.controller.service.datosRespuestaTopic;
import com.ForoHub.AluraChallenge.model.Curso;
import com.ForoHub.AluraChallenge.model.Topic;
import com.ForoHub.AluraChallenge.model.Usuario;
import com.ForoHub.AluraChallenge.repository.CursoRepository;
import com.ForoHub.AluraChallenge.repository.TopicsRepository;
import com.ForoHub.AluraChallenge.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicsController {
    @Autowired
    private TopicsRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    // Registro de un nuevo topic
    @PostMapping
    public ResponseEntity<Topic> registrar(@RequestBody datosRegistroTopic datos) {
        Usuario autor = usuarioRepository.findByNombre(datos.autor())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Curso curso = cursoRepository.findByNombre(datos.curso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Topic nuevoTopic = new Topic(datos.titulo(), datos.mensaje(), LocalDateTime.now(), autor, curso);
        nuevoTopic.setStatus(true);
        Topic topicGuardado = repository.save(nuevoTopic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicGuardado);
    }

    // Listado
    @GetMapping
    public ResponseEntity<List<datosRespuestaTopic>> listar() {
        List<datosRespuestaTopic> topics = repository.findAll().stream()
                .map(datosRespuestaTopic::new)
                .toList();
        return ResponseEntity.ok(topics);
    }

    // Detalles /{id}
    @GetMapping("/{id}")
    public ResponseEntity<Topic> detallar(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualización
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<datosRespuestaTopic> actualizar(@PathVariable Long id, @RequestBody datosRegistroTopic datos) {
        return repository.findById(id)
                .map(topic -> {
                    topic.setTitulo(datos.titulo());
                    topic.setMensaje(datos.mensaje());
                    topic.setStatus(datos.status());

                    Usuario autor = usuarioRepository.findByNombre(datos.autor())
                            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                    Curso curso = cursoRepository.findByNombre(datos.curso())
                            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

                    topic.setAutor(autor);
                    topic.setCurso(curso);
                    return ResponseEntity.ok(new datosRespuestaTopic(topic));
                })
                .orElse(ResponseEntity.notFound().build());
    }



    // Eliminación /{id}
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}