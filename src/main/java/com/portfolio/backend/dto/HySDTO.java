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

    public HySDTO() {
    }

    public HySDTO(String name, int percentage) {
        this.name = name;
        this.percentage = percentage;
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
    
}
