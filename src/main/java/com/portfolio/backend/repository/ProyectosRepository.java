/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.backend.repository;

import com.portfolio.backend.model.Proyectos;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego
 */

@Repository
public interface ProyectosRepository extends JpaRepository<Proyectos, Integer> {
    public Optional<Proyectos> findByProyecto(String proyecto);
    public List<Proyectos> findAllByUsuarioId(int id);
    public boolean existsByProyecto(String proyecto);
}
