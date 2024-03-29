package com.portfolio.backend.controller;

import com.portfolio.backend.dto.HySDTO;
import com.portfolio.backend.model.HyS;
import com.portfolio.backend.security.controller.Mensaje;
import com.portfolio.backend.security.entity.Usuario;
import com.portfolio.backend.security.service.UsuarioService;
import com.portfolio.backend.service.HySService;
import java.util.List;
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
@RequestMapping("/hys")
@CrossOrigin(origins = {"http://localhost:4200", "https://front-end-f038b.web.app"})
public class HySController {
    @Autowired
    HySService hysServ;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/lista")
    public ResponseEntity<List<HyS>> list(){
        List<HyS> list = hysServ.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody HySDTO hysDTO){
        if(StringUtils.isBlank(hysDTO.getName()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(hysServ.existsByName(hysDTO.getName()))
            return new ResponseEntity(new Mensaje("Esa skill ya existe"),HttpStatus.BAD_REQUEST);
        Usuario usuario = usuarioService.getUserById(hysDTO.getUsuarioId()).orElseThrow(() -> new RuntimeException("No se pudo encontrar el usuario con el ID proporcionado."));
        HyS hys = new HyS(hysDTO.getName(), hysDTO.getPercentage(), usuario);
        hysServ.save(hys);
        return new ResponseEntity(new Mensaje("Skill agregada"),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody HySDTO hysDTO){
        if(!hysServ.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        if(hysServ.existsByName(hysDTO.getName()) && hysServ.getByName(hysDTO.getName()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa skill ya existe"),HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(hysDTO.getName()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
        HyS hys = hysServ.getOne(id).get();
        hys.setName(hysDTO.getName());
        hys.setPercentage(hysDTO.getPercentage());
        hysServ.save(hys);
        return new ResponseEntity(new Mensaje("Skill actualizada"),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!hysServ.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        hysServ.delete(id);
        return new ResponseEntity(new Mensaje("Skill eliminada"),HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<HyS> getById(@PathVariable("id") int id){
        if(!hysServ.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        HyS hys = hysServ.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }
}