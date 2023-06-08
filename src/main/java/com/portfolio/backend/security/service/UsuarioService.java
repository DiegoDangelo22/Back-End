/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.security.service;

import com.portfolio.backend.security.entity.Usuario;
import com.portfolio.backend.security.repository.iUsuarioRepository;
import javax.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Diego
 */

@Service
@Transactional
public class UsuarioService {
    @Autowired
    iUsuarioRepository iusuarioRepository;
    
    public Usuario getByNombreUsuario(String nombreUsuario) {
        return iusuarioRepository.findByNombreUsuario(nombreUsuario);
    }
    
    public Optional<Usuario> getByPassword(String password) {
        return iusuarioRepository.findByPassword(password);
    }
    
    public boolean existsByNombreUsuario(String nombreUsuario) {
        return iusuarioRepository.existsByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByPassword(String password) {
        return iusuarioRepository.existsByPassword(password);
    }
    
    public void save(Usuario usuario) {
        iusuarioRepository.save(usuario);
    }
    
    public Optional<Usuario> getUserById(int id) {
        return iusuarioRepository.findById(id);
    }

}
