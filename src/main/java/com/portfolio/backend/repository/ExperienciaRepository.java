/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.backend.repository;

import com.portfolio.backend.model.Experiencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego
 */

@Repository
public interface ExperienciaRepository extends JpaRepository <Experiencia, Integer> {
    public Optional<Experiencia> findByNombreExp(String nombreExp);
    public boolean existsByNombreExp(String nombreExp);
}
