/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author Diego
 */

public class ProyectosDTO {
    @NotBlank
    private String proyecto;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String img;
    private int usuarioId;

    public ProyectosDTO() {
    }

    public ProyectosDTO(String proyecto, String descripcion, String img, int usuarioId) {
        this.proyecto = proyecto;
        this.descripcion = descripcion;
        this.img = img;
        this.usuarioId = usuarioId;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getImg() {
        return img;
    }
    
    public void setImg(String img) {
        this.img = img;
    }
    
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
    
}
