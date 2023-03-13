/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.Educacion;
import com.portfolio.backend.repository.EducacionRepository;
import com.portfolio.backend.security.entity.Usuario;
import com.portfolio.backend.security.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Diego
 */

@Service
@Transactional
public class EducacionService {
    @Autowired
    EducacionRepository educacionRepository;
    
    public List<Educacion> list(){
        return educacionRepository.findAll();
    }
    
    public Optional<Educacion> getOne(int id){
        return educacionRepository.findById(id);
    }
    
    public Optional<Educacion> getByNombreEdu(String nombreEdu){
        return educacionRepository.findByNombreEdu(nombreEdu);
    }
    
    public void save(Educacion educacion){
        educacionRepository.save(educacion);
    }
    
    public void delete(int id){
        educacionRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return educacionRepository.existsById(id);
    }
    
    public boolean existsByNombreEdu(String nombreEdu){
        return educacionRepository.existsByNombreEdu(nombreEdu);
    }
    
    @Autowired
    private UsuarioService usuarioService;
    
    public Educacion saveInformation(Educacion educacion, int userId) {
        Optional<Usuario> usuarioOptional = usuarioService.getUserById(userId);
        Usuario usuario = usuarioOptional.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        educacion.setUsuario(usuario);
        return educacionRepository.save(educacion);
    }
    
    public List<Educacion> getAllEducacionesByUsuario(int usuarioId) {
        return educacionRepository.findAllByUsuarioId(usuarioId);
    }

}
