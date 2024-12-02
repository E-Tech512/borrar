package com.crud.start.Controller;

import com.crud.start.DTO.UserDTO;
import com.crud.start.Model.User;
import com.crud.start.Service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("http://localhost:4200/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            // Llamamos al servicio para registrar al usuario
            ResponseEntity<?> registeredUser = userService.registrarUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (IllegalArgumentException ex) {
            // Capturamos la excepción cuando la contraseña no cumple con los requisitos
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDto) {
        Optional<User> user = userService.autenticarUser(userDto.getEmail(), userDto.getPassword());
        return user.isPresent() ? "Login exitoso" : "Credenciales incorrectas";
    }


    @PostMapping("/recover")
    public String recoverPassword(@RequestParam String email) {
        Optional<User> user = userService.generarTokenRecuperacion(email);
        return user.isPresent() ? "Se envió un enlace de recuperación a tu correo" : "Correo no encontrado";
    }

    @PostMapping("/update-password")
    public String updatePassword(@RequestParam String token, @RequestParam String newPassword) {
        Optional<User> userOpt = userService.obtenerUserPorToken(token);
        if (userOpt.isPresent()) {
            userService.actualizarContraseña(userOpt.get(), newPassword);
            return "Contraseña actualizada con éxito";
        }
        return "Token inválido o expirado";
    }
}
