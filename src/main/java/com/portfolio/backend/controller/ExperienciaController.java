/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.controller;

import org.apache.commons.lang3.StringUtils;
import com.portfolio.backend.dto.ExperienciaDTO;
import com.portfolio.backend.model.Experiencia;
import com.portfolio.backend.security.controller.Mensaje;
import com.portfolio.backend.service.ExperienciaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/explab")
@CrossOrigin(origins = {"http://localhost:4200", "https://front-end-f038b.web.app"})
public class ExperienciaController {
    @Autowired
    ExperienciaService experienciaService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = experienciaService.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ExperienciaDTO expdto){
//        if(StringUtils.isBlank(expdto.getNombreExp()))
//            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
//        if(experienciaService.existsByNombreExp(expdto.getNombreExp()))
//            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"),HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = new Experiencia(expdto.getNombreExp(), expdto.getDescripcionExp(), expdto.getUsuario());
        experienciaService.save(experiencia);
        
        return new ResponseEntity(new Mensaje("Experiencia agregada"),HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ExperienciaDTO expdto){
        if(!experienciaService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        if(experienciaService.existsByNombreExp(expdto.getNombreExp()) && experienciaService.getByNombreExp(expdto.getNombreExp()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"),HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(expdto.getNombreExp()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = experienciaService.getOne(id).get();
        experiencia.setNombreExp(expdto.getNombreExp());
        experiencia.setDescripcionExp(expdto.getDescripcionExp());
        
        experienciaService.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"),HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!experienciaService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        
        experienciaService.delete(id);
        
        return new ResponseEntity(new Mensaje("Experiencia eliminada"),HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!experienciaService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = experienciaService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);

    }
    
}
