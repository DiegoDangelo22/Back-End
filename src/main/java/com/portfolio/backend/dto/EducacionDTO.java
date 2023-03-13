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

public class EducacionDTO {

    private String nombreEdu;

    private String descripcionEdu;
    
    private int usuarioId;

    public EducacionDTO() {
    }

    
    public EducacionDTO(String nombreEdu, String descripcionEdu, int usuarioId) {
        this.nombreEdu = nombreEdu;
        this.descripcionEdu = descripcionEdu;
        this.usuarioId = usuarioId;
    }

    public String getNombreEdu() {
        return nombreEdu;
    }

    public void setNombreEdu(String nombreEdu) {
        this.nombreEdu = nombreEdu;
    }

    public String getDescripcionEdu() {
        return descripcionEdu;
    }

    public void setDescripcionEdu(String descripcionEdu) {
        this.descripcionEdu = descripcionEdu;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    
    
}
