/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.HyS;
import com.portfolio.backend.repository.HySRepository;
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
}
