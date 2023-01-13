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

public class PersonaDTO {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String descripcion;  
    @NotBlank
    private String profesion;
    @NotBlank
    private String img;

    // Constructor
    
    public PersonaDTO() {
    }

    public PersonaDTO(String nombre, String apellido, String descripcion, String profesion, String img) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.profesion = profesion;
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getProfesion() {
        return profesion;
    }
    
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
}
