package com.portfolio.backend.service;

import com.portfolio.backend.model.Persona;
import com.portfolio.backend.repository.PersonaRepository;
import com.portfolio.backend.security.entity.Usuario;
import com.portfolio.backend.security.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonaService {
    @Autowired
    private PersonaRepository persoRepository;

    public List<Persona> list(){
        return persoRepository.findAll();
    }
    
    public Optional<Persona> getOne(int id){
        return persoRepository.findById(id);
    }
    
    public Optional<Persona> getByNombre(String nombre){
        return persoRepository.findByNombre(nombre);
    }
    
    public void save(Persona persona){
        persoRepository.save(persona);
    }
    
    public void delete(int id){
        persoRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return persoRepository.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return persoRepository.existsByNombre(nombre);
    }
    
    @Autowired
    private UsuarioService usuarioService;
    
    public Persona saveInformation(Persona persona, int userId) {
        Optional<Usuario> usuarioOptional = usuarioService.getUserById(userId);
        Usuario usuario = usuarioOptional.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        persona.setUsuario(usuario);
        return persoRepository.save(persona);
    }
    
    public List<Persona> getAllPersonasByUsuario(int usuarioId) {
        return persoRepository.findAllByUsuarioId(usuarioId);
    }
    
}
