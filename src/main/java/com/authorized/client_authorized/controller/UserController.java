package com.authorized.client_authorized.controller;


import com.authorized.client_authorized.model.User;
import com.authorized.client_authorized.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // GET all users
    @GetMapping
    public List<User> getAllUsuarios() {
        return userRepository.findAll();
    }

    // GET user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUsuarioById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(usuario -> ResponseEntity.ok().body(usuario))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create new user
    @PostMapping
    public User createUsuario(@RequestBody User usuario) {
        return userRepository.save(usuario);
    }

    // PUT update user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUsuario(@PathVariable Long id, @RequestBody User usuarioDetails) {
        return userRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(usuarioDetails.getNome());
                    usuario.setEmail(usuarioDetails.getEmail());
                    User updatedUsuario = userRepository.save(usuario);
                    return ResponseEntity.ok().body(updatedUsuario);
                }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE user
    @DeleteMapping("/{id}")
    public HttpEntity<Object> deleteUsuario(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(u -> {
                    userRepository.delete(u);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
