package com.portfolio.backend.model;

import com.portfolio.backend.security.entity.Usuario;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
//    @NotNull
//    @Size(min = 1, max = 50)
    private String nombre;
    
//    @NotNull
//    @Size(min = 1, max = 50)
    private String apellido;
    
//    @NotNull
    private String descripcion;
    
//    @NotNull
//    @Size(min = 1, max = 35)
    private String profesion;
//    @NotNull
    private String img;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Usuario usuario;

    // Constructor
    
    public Persona() {
    }

    public Persona(String nombre, String apellido, String descripcion, String profesion, String img, Usuario usuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.profesion = profesion;
        this.img = img;
        this.usuario = usuario;
    }
    
    // Getter y Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
