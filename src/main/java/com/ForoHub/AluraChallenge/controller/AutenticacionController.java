/*package com.ForoHub.AluraChallenge.controller;


import com.ForoHub.AluraChallenge.model.LoginRequest;
import com.ForoHub.AluraChallenge.model.Usuario;
import com.ForoHub.AluraChallenge.security.AutenticacionService;
import com.ForoHub.AluraChallenge.security.JwtData;
import com.ForoHub.AluraChallenge.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AutenticacionService autenticacionService;

    @Autowired
    private TokenService tokenService;

//Probar este código
@PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(), datosAutenticacionUsuario.clave());
       var datosAutenticados = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) datosAutenticados.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

 */
    /*@PostMapping
    public ResponseEntity<JwtData> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            // Usamos AutenticacionService para autenticar al usuario
            UsernamePasswordAuthenticationToken authenticationToken =
                    autenticacionService.autenticar(loginRequest.getEmail(), loginRequest.getContrasena());

            // Si la autenticación es correcta, obtenemos el usuario y generamos el token
            Usuario usuario = (Usuario) authenticationToken.getPrincipal();
            String token = tokenService.generarToken(usuario);

            return ResponseEntity.ok(new JwtData(token));

        } catch (AuthenticationException e) {
            // Si la autenticación falla, devolvemos un 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}*/


    /*@PostMapping
    public ResponseEntity<JwtData> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Crear el token de autenticación
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(), loginRequest.getContrasena());

            // Autenticar al usuario
            Authentication authResult = authenticationManager.authenticate(authentication);

            // Obtener el usuario autenticado
            Usuario usuario = (Usuario) authResult.getPrincipal();

            // Generar el token JWT
            String token = tokenService.generarToken(usuario);

            return ResponseEntity.ok(new JwtData(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}*/


