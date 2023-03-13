/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.controller;

import com.portfolio.backend.dto.EducacionDTO;
import com.portfolio.backend.model.Educacion;
import com.portfolio.backend.security.controller.Mensaje;
import com.portfolio.backend.security.entity.Usuario;
import com.portfolio.backend.security.service.UsuarioService;
import com.portfolio.backend.service.EducacionService;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Diego
 */

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = {"http://localhost:4200", "https://front-end-f038b.web.app"})
public class EducacionController {
    @Autowired
    private EducacionService educacionService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EntityManager entityManager;
    
    @GetMapping("/educaciones-by-user")
    public ResponseEntity<List<Educacion>> getAllEducacionesByUsuario(HttpServletRequest request) {
        int usuarioId = getUserId(request);
        List<Educacion> educaciones = educacionService.getAllEducacionesByUsuario(usuarioId);
        return ResponseEntity.ok().body(educaciones);
    }

    @GetMapping("/user-id")
    public Integer getUserId(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Usuario> optionalUsuario = usuarioService.getByNombreUsuario(username);
        Usuario usuario = optionalUsuario.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        Integer usuarioId = usuario.getId();
        return usuarioId;
    }
    
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = educacionService.list();
        list.forEach(educacion -> Hibernate.initialize(educacion.getUsuario()));
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        if(!educacionService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.NOT_FOUND);
        }
        
        Educacion educacion = educacionService.getOne(id).get();
        return new ResponseEntity(educacion,HttpStatus.OK);
    }
    
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!educacionService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.NOT_FOUND);
        }
        educacionService.delete(id);
        return new ResponseEntity(new Mensaje("Educación eliminada"),HttpStatus.OK);
    }
    
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EducacionDTO educacionDTO){
        Usuario usuario = usuarioService.getUserById(educacionDTO.getUsuarioId()).orElseThrow(() -> new RuntimeException("No se pudo encontrar el usuario con el ID proporcionado."));
        Educacion educacion = new Educacion(educacionDTO.getNombreEdu(), educacionDTO.getDescripcionEdu(), usuario);
        educacionService.save(educacion);
        return new ResponseEntity(new Mensaje("Educación creada"),HttpStatus.OK);
    }
    
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody EducacionDTO educacionDTO){
        if(!educacionService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.NOT_FOUND);
        }
//        if(educacionService.existsByNombreEdu(educacionDTO.getNombreEdu()) && educacionService.getByNombreEdu(educacionDTO.getNombreEdu()).get().getId() != id){
//            return new ResponseEntity(new Mensaje("Ese nombre ya existe"),HttpStatus.BAD_REQUEST);
//        }
//        if(StringUtils.isBlank(educacionDTO.getNombreEdu())){
//            return new ResponseEntity(new Mensaje("El campo no puede estar vacío"),HttpStatus.BAD_REQUEST);
//        }
        
        Educacion educacion = educacionService.getOne(id).get();
        
        educacion.setNombreEdu(educacionDTO.getNombreEdu());
        educacion.setDescripcionEdu(educacionDTO.getDescripcionEdu());
        
        educacionService.save(educacion);
        
        return new ResponseEntity(new Mensaje("Educación actualizada"),HttpStatus.OK);
    }
}
