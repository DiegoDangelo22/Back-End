/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.dto;

import com.portfolio.backend.security.entity.Usuario;



/**
 *
 * @author Diego
 */

public class ExperienciaDTO {

    private String nombreExp;

    private String descripcionExp;
    
    private int usuarioId;
    
    // Constructores

    public ExperienciaDTO() {
    }

    public ExperienciaDTO(String nombreExp, String descripcionExp, int usuarioId) {
        this.nombreExp = nombreExp;
        this.descripcionExp = descripcionExp;
        this.usuarioId = usuarioId;
    }
    
    // Getters y Setters

    public String getNombreExp() {
        return nombreExp;
    }

    public void setNombreExp(String nombreExp) {
        this.nombreExp = nombreExp;
    }

    public String getDescripcionExp() {
        return descripcionExp;
    }

    public void setDescripcionExp(String descripcionExp) {
        this.descripcionExp = descripcionExp;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
    
}
