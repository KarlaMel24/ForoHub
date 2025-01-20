package com.ForoHub.AluraChallenge.security;

import com.ForoHub.AluraChallenge.model.Usuario;
import com.ForoHub.AluraChallenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public AutenticacionService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }
}


/*@Service
public class AutenticacionService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsernamePasswordAuthenticationToken autenticar(String email, String contrasena) {
        // Buscar el usuario por email
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Email o contraseña incorrectos"));

        // Verificar la contraseña
        if (!passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            throw new BadCredentialsException("Email o contraseña incorrectos");
        }

        // Si la contraseña es correcta, crear un token de autenticación
        UserDetails userDetails = User.builder()
                .username(usuario.getEmail())
                .password(usuario.getContrasena())
                .roles("USER") // Asegúrate de asignar roles adecuados aquí
                .build();

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}*/


/*package com.ForoHub.AluraChallenge.security;

import com.ForoHub.AluraChallenge.model.Usuario;
import com.ForoHub.AluraChallenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + email));
    }

}*/

// Este es el código que funcina, lo vamos a comentar
/*@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca el usuario por email en la base de datos
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + email));

        // Devuelve el usuario cargado con sus detalles (como autoridades)
        return usuario;
    }

    public boolean validarContrasena(String contrasena, String contrasenaCodificada) {
        return passwordEncoder.matches(contrasena, contrasenaCodificada);
    }

    // Método adicional para autenticar el usuario manualmente
    public UsernamePasswordAuthenticationToken autenticar(String email, String contrasena) {
        Usuario usuario = (Usuario) loadUserByUsername(email);

        // Verificar la contraseña
        if (validarContrasena(contrasena, usuario.getContrasena())) {
            return new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        } else {
            throw new RuntimeException("Credenciales incorrectas");
        }
    }
}
*/
