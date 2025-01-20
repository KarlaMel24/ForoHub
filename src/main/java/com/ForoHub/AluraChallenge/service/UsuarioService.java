package com.ForoHub.AluraChallenge.service;

import com.ForoHub.AluraChallenge.model.Usuario;
import com.ForoHub.AluraChallenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(String email, String contrasena) {
        String contrasenaCifrada = passwordEncoder.encode(contrasena); // Cifra la contraseña
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setContrasena(contrasenaCifrada);
        return usuarioRepository.save(usuario);
    }
}

