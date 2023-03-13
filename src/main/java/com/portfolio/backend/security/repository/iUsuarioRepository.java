/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.backend.security.repository;

import com.portfolio.backend.security.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego
 */

@Repository
public interface iUsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    Optional<Usuario> findByPassword(String password);
    
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByPassword(String password);
}
