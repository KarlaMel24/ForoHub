package com.ForoHub.AluraChallenge.controller;

import com.ForoHub.AluraChallenge.controller.service.datosRegistroTopic;
import com.ForoHub.AluraChallenge.controller.service.datosRespuestaTopic;
import com.ForoHub.AluraChallenge.model.Curso;
import com.ForoHub.AluraChallenge.model.Topic;
import com.ForoHub.AluraChallenge.model.Usuario;
import com.ForoHub.AluraChallenge.repository.CursoRepository;
import com.ForoHub.AluraChallenge.repository.TopicsRepository;
import com.ForoHub.AluraChallenge.repository.UsuarioRepository;
import com.ForoHub.AluraChallenge.service.TopicsService;
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


    // Listado con el código viejo
    /*@GetMapping
    public ResponseEntity<List<Topic>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }*/
    // Listado con el código para obtener todos los campos
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

    // Actualización /{id} con el código viejo
    /*@PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Topic> actualizar(@PathVariable Long id, @RequestBody datosRegistroTopic datos) {
        return repository.findById(id)
                .map(topic -> {
                    topic.setTitulo(datos.titulo());
                    topic.setMensaje(datos.mensaje());

                    Usuario autor = usuarioRepository.findByNombre(datos.autor())
                            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                    Curso curso = cursoRepository.findByNombre(datos.curso())
                            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

                    topic.setAutor(autor);
                    topic.setCurso(curso);
                    return ResponseEntity.ok(topic);
                })
                .orElse(ResponseEntity.notFound().build());
    }*/

    // Actualización con el código nuevo
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



/*@RestController
@RequestMapping("/topics")
public class TopicsController {
    @Autowired
    private TopicsRepository repository;
    @Autowired
    private TopicsService service;

    // Registro de un nuevo topic
    // Post, datos: título, mensaje, autor y curso. Guardar en una vase de datos
    // Consejos: JpaRepository,findAll
    @PostMapping
    public ResponseEntity<Topic> registrar(@RequestBody Topic topic){
        Topic topicGuardado = service.guardar(topic);
        System.out.println("Registrar llega");
        return ResponseEntity.status(HttpStatus.CREATED).body(topicGuardado);
        //return repository.save(new Topic(datosRegistroTopic));
    }

    // Listado
    // Get, datos: título, mensaje, fecha de creación, estado, autor y curso
    // Regresar los datos en formato JSON
    // Consejos: JpaRepository, findALL
    @GetMapping
    public ResponseEntity<List<Topic>> listar(){
        //var response = service.listar();
        System.out.println("listar llega");
        //return ResponseEntity.ok(response);
        return repository.findAll().stream().map();
    }

    // Detalles /{id}
    // Get, datos: título, mensaje, fecha de creación, estado, autor y curso
    // Formato Json
    // Regla de negocio: Solicitar ID para realizar la consulta
    // Consejos: @PathVariable
    @GetMapping("{id}")
    public ResponseEntity<Topic> detallar(@PathVariable Long id){
        Topic topic = service.buscarPorId(id);
        System.out.println("detallar llega");
        if(topic != null){
            return ResponseEntity.ok(topic);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    // Actualización /{id}
    // Put
    // Regla de negocio: Solicitar ID
    // Consejos: @PathVariable, isPresent()
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<Topic> actualizar(@PathVariable Long id, @RequestBody Topic topic){
        if ()
            return service.buscarPorId(id)
                    .map()
    }


    // Eliminación /{id}
    // Delete
    // Solicitar ID
    // Consejos: @PathVariable, isPresent(), deleteById
    @DeleteMapping({"id"})
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        var topic = repository.getReferenceById(id);
        topic.eliminar();
    }
}*/
