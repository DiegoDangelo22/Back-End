/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.backend.repository;

import com.portfolio.backend.model.HyS;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Diego
 */

public interface HySRepository extends JpaRepository<HyS, Integer> {
    public Optional<HyS> findByName(String name);
    public boolean existsByName(String name);
}
