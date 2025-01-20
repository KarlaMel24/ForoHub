package com.ForoHub.AluraChallenge.service;

import com.ForoHub.AluraChallenge.model.Topic;
import com.ForoHub.AluraChallenge.repository.TopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicsService {
    @Autowired
    private TopicsRepository repository;

    public List<Topic> listar() {
        return repository.findAll();
    }

    public Topic guardar(Topic topic) {
        return repository.save(topic);
    }

    public Topic buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
}
