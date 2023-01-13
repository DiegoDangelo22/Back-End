/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Diego
 */

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(unique=true)
    private String nombreUsuario;
    @NotNull
    private String password;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="usuario_rol", joinColumns=@JoinColumn(name="usuario_id"), inverseJoinColumns=@JoinColumn(name="rol_id"))
    private Set<Rol> roles = new HashSet<>();
    
    // Constructores

    public Usuario() {
    }

    public Usuario(String nombreUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }
    
    // Getter y Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
    
}
