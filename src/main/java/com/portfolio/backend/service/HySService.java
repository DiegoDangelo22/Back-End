/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.HyS;
import com.portfolio.backend.repository.HySRepository;
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

@Transactional
@Service
public class HySService {
    @Autowired
    HySRepository hysRepo;
    
    public List<HyS> list() {
        return hysRepo.findAll();
    }
    
    public Optional<HyS> getOne(int id) {
        return hysRepo.findById(id);
    }
    
    public Optional<HyS> getByName(String name) {
        return hysRepo.findByName(name);
    }
    
    public void save(HyS skill) {
        hysRepo.save(skill);
    }
    
    public void delete(int id) {
        hysRepo.deleteById(id);
    }
    
    public boolean existsById(int id) {
        return hysRepo.existsById(id);
    }
    
    public boolean existsByName(String name) {
        return hysRepo.existsByName(name);
    }
    
    @Autowired
    private UsuarioService usuarioService;
    
    public HyS saveInformation(HyS hys, int userId) {
        Optional<Usuario> usuarioOptional = usuarioService.getUserById(userId);
        Usuario usuario = usuarioOptional.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        hys.setUsuario(usuario);
        return hysRepo.save(hys);
    }
    
    public List<HyS> getAllHySByUsuario(int usuarioId) {
        return hysRepo.findAllByUsuarioId(usuarioId);
    }
    
}
