/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.Proyectos;
import com.portfolio.backend.repository.ProyectosRepository;
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
public class ProyectosService {
    @Autowired
    ProyectosRepository proyectosRepo;
    
    public List<Proyectos> list(){
        return proyectosRepo.findAll();
    }
    
    public Optional<Proyectos> getOne(int id){
        return proyectosRepo.findById(id);
    }
    
    public Optional<Proyectos> getByProyecto(String proyecto){
        return proyectosRepo.findByProyecto(proyecto);
    }
    
    public void save(Proyectos proyectos){
        proyectosRepo.save(proyectos);
    }
    
    public void delete(int id){
        proyectosRepo.deleteById(id);
    }
    
    public boolean existsById(int id){
        return proyectosRepo.existsById(id);
    }
    
    public boolean existsByProyecto(String proyecto){
        return proyectosRepo.existsByProyecto(proyecto);
    }
}
