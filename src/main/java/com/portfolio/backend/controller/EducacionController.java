package com.portfolio.backend.controller;

import com.portfolio.backend.dto.EducacionDTO;
import com.portfolio.backend.model.Educacion;
import com.portfolio.backend.security.controller.Mensaje;
import com.portfolio.backend.security.entity.Usuario;
import com.portfolio.backend.security.service.UsuarioService;
import com.portfolio.backend.service.EducacionService;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = {"http://localhost:4200", "https://front-end-f038b.web.app"})
public class EducacionController {
    @Autowired
    private EducacionService educacionService;
    @Autowired
    private UsuarioService usuarioService;
    
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!educacionService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.NOT_FOUND);
        }
        educacionService.delete(id);
        return new ResponseEntity(new Mensaje("Educación eliminada"),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EducacionDTO educacionDTO){
        Usuario usuario = usuarioService.getUserById(educacionDTO.getUsuarioId()).orElseThrow(() -> new RuntimeException("No se pudo encontrar el usuario con el ID proporcionado."));
        Educacion educacion = new Educacion(educacionDTO.getNombreEdu(), educacionDTO.getDescripcionEdu(), usuario);
        educacionService.save(educacion);
        return new ResponseEntity(new Mensaje("Educación creada"),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody EducacionDTO educacionDTO){
        if(!educacionService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.NOT_FOUND);
        }
        Educacion educacion = educacionService.getOne(id).get();
        educacion.setNombreEdu(educacionDTO.getNombreEdu());
        educacion.setDescripcionEdu(educacionDTO.getDescripcionEdu());
        educacionService.save(educacion);
        return new ResponseEntity(new Mensaje("Educación actualizada"),HttpStatus.OK);
    }
}