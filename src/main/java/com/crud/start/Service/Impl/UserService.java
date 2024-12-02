package com.crud.start.Service.Impl;

import com.crud.start.Model.User;
import com.crud.start.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])[A-Za-z\\d@#$%^&+=!]{8,}$";

    public ResponseEntity<?> registrarUser(User user) {
        if (!Pattern.matches(PASSWORD_PATTERN, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La contraseña debe tener al menos una letra mayúscula, una minúscula, un número, un signo y ser de al menos 8 caracteres.");
        }

        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    public Optional<User> autenticarUser(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password));
    }

    public Optional<User> generarTokenRecuperacion(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        userOpt.ifPresent(user -> {
            user.setResetToken(UUID.randomUUID().toString());
            userRepository.save(user);
        });
        return userOpt;
    }

    public Optional<User> obtenerUserPorToken(String token) {
        return userRepository.findByResetToken(token);
    }

    public void actualizarContraseña(User user, String nuevaPassword) {
        user.setPassword(nuevaPassword);
        user.setResetToken(null);
        userRepository.save(user);
    }


}
