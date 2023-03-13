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

public class HySDTO {
    @NotBlank
    private String name;
    @NotBlank
    private int percentage;
    private int usuarioId;

    public HySDTO() {
    }

    public HySDTO(String name, int percentage, int usuarioId) {
        this.name = name;
        this.percentage = percentage;
        this.usuarioId = usuarioId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
    
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
    
}
