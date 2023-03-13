package com.portfolio.backend.controller;

import com.portfolio.backend.dto.PersonaDTO;
import com.portfolio.backend.model.Persona;
import com.portfolio.backend.security.controller.Mensaje;
import com.portfolio.backend.security.entity.Usuario;
import com.portfolio.backend.security.service.UsuarioService;
import com.portfolio.backend.service.PersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = {"http://localhost:4200", "https://front-end-f038b.web.app"})
public class PersonaController {
    @Autowired
    PersonaService persoServ;
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = persoServ.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!persoServ.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.NOT_FOUND);
        }
        
        Persona persona = persoServ.getOne(id).get();
        return new ResponseEntity(persona,HttpStatus.OK);
    }
    
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!persoServ.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.NOT_FOUND);
        }
        persoServ.delete(id);
        return new ResponseEntity(new Mensaje("Persona eliminada"),HttpStatus.OK);
    }
    
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PersonaDTO personaDTO){
      Usuario usuario = usuarioService.getUserById(personaDTO.getUsuarioId()).orElseThrow(() -> new RuntimeException("No se pudo encontrar el usuario con el ID proporcionado."));
        Persona persona = new Persona(personaDTO.getNombre(), personaDTO.getApellido(), personaDTO.getDescripcion(), personaDTO.getProfesion(), personaDTO.getImg(), usuario);
        persoServ.save(persona);
        return new ResponseEntity(new Mensaje("Persona creada"),HttpStatus.OK);
    }
    
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody PersonaDTO personaDTO){
        if(!persoServ.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.NOT_FOUND);
        }
        if(persoServ.existsByNombre(personaDTO.getNombre()) && persoServ.getByNombre(personaDTO.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(personaDTO.getNombre())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacío"),HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = persoServ.getOne(id).get();
        
        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());
        persona.setDescripcion(personaDTO.getDescripcion());
        persona.setProfesion(personaDTO.getProfesion());
        persona.setImg(personaDTO.getImg());
        persona.getImg();
        
        persoServ.save(persona);
        
        return new ResponseEntity(new Mensaje("Persona actualizada"),HttpStatus.OK);
    }
}