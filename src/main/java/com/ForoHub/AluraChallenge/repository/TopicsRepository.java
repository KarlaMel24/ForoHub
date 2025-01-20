package com.ForoHub.AluraChallenge.repository;


import com.ForoHub.AluraChallenge.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

// Intermediario entre la interfaz y la aplicación. Realiza operaciones CRUD
// Topic es la entidad que manejará, Long es el tipo de dato del id
// Solo se escribe código para métodos personalizados
@Repository
public interface TopicsRepository extends JpaRepository<Topic, Long>{
    UserDetails findByid(Long id);
}
