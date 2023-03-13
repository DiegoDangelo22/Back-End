/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.controller;

import com.portfolio.backend.model.Educacion;
import com.portfolio.backend.security.entity.Usuario;
import com.portfolio.backend.security.service.UsuarioService;
import com.portfolio.backend.service.EducacionService;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Diego
 */

@RestController
@RequestMapping("/auth")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EducacionService educacionService;

    @GetMapping("/user-id")
    public Integer getUserId(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Usuario> optionalUsuario = usuarioService.getByNombreUsuario(username);
        Usuario usuario = optionalUsuario.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        Integer usuarioId = usuario.getId();
        return usuarioId;
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(value = "id") int userId) {
        Optional<Usuario> usuario = usuarioService.getUserById(userId);
        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(usuario.get());
    }
    
    @PostMapping("/users/{id}/information")
    public ResponseEntity<Educacion> saveInformation(@PathVariable(value = "id") int userId, @RequestBody Educacion educacion) {
        Educacion savedInformation = educacionService.saveInformation(educacion, userId);
        return ResponseEntity.ok().body(savedInformation);
    }
}

