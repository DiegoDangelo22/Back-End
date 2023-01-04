/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.backend.security.repository;

import com.portfolio.backend.security.entity.Rol;
import com.portfolio.backend.security.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego
 */

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
