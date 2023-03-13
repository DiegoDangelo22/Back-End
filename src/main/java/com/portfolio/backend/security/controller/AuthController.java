/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.security.controller;

import com.portfolio.backend.model.Educacion;
import com.portfolio.backend.security.dto.JwtDTO;
import com.portfolio.backend.security.dto.LoginUsuario;
import com.portfolio.backend.security.dto.NuevoUsuario;
import com.portfolio.backend.security.entity.Rol;
import com.portfolio.backend.security.entity.Usuario;
import com.portfolio.backend.security.entity.UsuarioPrincipal;
import com.portfolio.backend.security.enums.RolNombre;
import com.portfolio.backend.security.jwt.JwtProvider;
import com.portfolio.backend.security.service.RolService;
import com.portfolio.backend.security.service.UsuarioService;
import com.portfolio.backend.service.EducacionService;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = {"http://localhost:4200", "https://front-end-f038b.web.app"})
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    EducacionService educacionService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;
    
//    @GetMapping("/user-id")
//    public Integer getUserId(HttpServletRequest request) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        Optional<Usuario> optionalUsuario = usuarioService.getByNombreUsuario(username);
//        Usuario usuario = optionalUsuario.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
//        Integer usuarioId = usuario.getId();
//        return usuarioId;
//    }
    
    
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos incorrectos"),HttpStatus.BAD_REQUEST);
        
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("Ese nombre de usuario ya existe"),HttpStatus.BAD_REQUEST);
            
        Usuario usuario = new Usuario(nuevoUsuario.getNombreUsuario(), passwordEncoder.encode(nuevoUsuario.getPassword()));
        
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        
        // Creamos el objeto Authentication con el usuario recién registrado
        Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
        nuevoUsuario.getNombreUsuario(),
        nuevoUsuario.getPassword()
            )
        );

        // Generamos el token
        String token = jwtProvider.generateToken(authentication);

        System.out.println(token);
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
    
        int userId = usuarioPrincipal.getId();
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED)
                     .body(new JwtDTO(token,usuarioPrincipal.getUsername(), userId, usuarioPrincipal.getAuthorities()));


//        return new ResponseEntity(new Mensaje("Usuario guardado"),HttpStatus.CREATED);
        }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos obligatorios"),HttpStatus.BAD_REQUEST);
        if(!usuarioService.existsByNombreUsuario(loginUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("Ese usuario no existe"), HttpStatus.BAD_REQUEST);
        if(!usuarioService.existsByNombreUsuario(loginUsuario.getPassword()))
            return new ResponseEntity(new Mensaje("Contraseña incorecta"), HttpStatus.BAD_REQUEST);
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String token = jwtProvider.generateToken(authentication);
        
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
    
        int userId = usuarioPrincipal.getId();
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        JwtDTO jwtDTO = new JwtDTO(token, usuarioPrincipal.getUsername(), userId, usuarioPrincipal.getAuthorities());
        
        return new ResponseEntity(jwtDTO,HttpStatus.OK);
    }

}