package com.portfolio.backend.controller;

import com.portfolio.backend.dto.ProyectosDTO;
import com.portfolio.backend.model.Proyectos;
import com.portfolio.backend.security.controller.Mensaje;
import com.portfolio.backend.security.entity.Usuario;
import com.portfolio.backend.security.service.UsuarioService;
import com.portfolio.backend.service.ProyectosService;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = {"http://localhost:4200", "https://front-end-f038b.web.app"})
public class ProyectosController {
    @Autowired
    ProyectosService proyectosService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/data")
    public Integer getData() {
        Query query = entityManager.createNativeQuery("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'portfolio' AND TABLE_NAME = 'proyectos'");
        return ((BigInteger) query.getSingleResult()).intValue();
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list(){
        List<Proyectos> list = proyectosService.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") int id){
        if(!proyectosService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.NOT_FOUND);
        }
        Proyectos proyecto = proyectosService.getOne(id).get();
        return new ResponseEntity(proyecto,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!proyectosService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.NOT_FOUND);
        }
        proyectosService.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminado"),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProyectosDTO proyectosDTO){
        if(StringUtils.isBlank(proyectosDTO.getProyecto())){
            return new ResponseEntity(new Mensaje("Nombre obligatorio"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(proyectosDTO.getDescripcion())){
            return new ResponseEntity(new Mensaje("Descripción obligatoria"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(proyectosDTO.getImg())){
            return new ResponseEntity(new Mensaje("Imagen obligatoria"),HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = usuarioService.getUserById(proyectosDTO.getUsuarioId()).orElseThrow(() -> new RuntimeException("No se pudo encontrar el usuario con el ID proporcionado."));
        Proyectos proyecto = new Proyectos(proyectosDTO.getProyecto(), proyectosDTO.getDescripcion(), proyectosDTO.getImg(), usuario);
        proyectosService.save(proyecto);
        return new ResponseEntity(new Mensaje("Proyecto creado"),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProyectosDTO proyectosDTO){
        if(!proyectosService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.NOT_FOUND);
        }
        if(proyectosService.existsByProyecto(proyectosDTO.getProyecto()) && proyectosService.getByProyecto(proyectosDTO.getProyecto()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"),HttpStatus.BAD_REQUEST);
        }
        Proyectos proyecto = proyectosService.getOne(id).get();
        proyecto.setProyecto(proyectosDTO.getProyecto());
        proyecto.setDescripcion(proyectosDTO.getDescripcion());
        proyecto.setImg(proyectosDTO.getImg());
        proyecto.getImg();
        proyectosService.save(proyecto);
        return new ResponseEntity(new Mensaje("Proyecto actualizado"),HttpStatus.OK);
    }
}