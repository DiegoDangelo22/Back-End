package com.portfolio.backend.controller;

import com.portfolio.backend.model.Persona;
import com.portfolio.backend.service.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonaController {
    @Autowired
    private IPersonaService interPersona;
    
    @GetMapping ("/personas/traer")
    public List<Persona> getPersonas() {
        return interPersona.getPersonas();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping ("/personas/crear")
    public String createStudent(@RequestBody Persona perso) {
        interPersona.savePersona(perso);
        return "La persona fue creada correctamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/personas/borrar/{id}")
    public String deletePersona (@PathVariable Long id) {
        interPersona.deletePersona(id);
        return "La persona fue eliminada correctamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping ("personas/editar/{id}")
    public Persona editPersona (@PathVariable Long id,
                                @RequestParam ("nombre") String newNombre,
                                @RequestParam ("apellido") String newApellido,
                                @RequestParam ("img") String newImg) {
        Persona perso = interPersona.findPersona(id);
        perso.setNombre(newNombre);
        perso.setApellido(newApellido);
        perso.setImg(newImg);
        interPersona.savePersona(perso);
        return perso;
    }
}
