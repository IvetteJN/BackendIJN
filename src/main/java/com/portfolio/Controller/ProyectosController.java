package com.portfolio.Controller;

import com.portfolio.Dto.DtoProyectos;
import com.portfolio.Entity.Proyectos;
import com.portfolio.Security.Controller.Mensaje;
import com.portfolio.Service.ProyectosService;
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
@RequestMapping("/proyectos")
@CrossOrigin(origins = "https://portfolio-ivette-jael-no-a42d5.web.app")

public class ProyectosController {
    @Autowired
    ProyectosService sProyectos;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list(){
        List<Proyectos> list = sProyectos.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id")int id){
        if(!sProyectos.existsById(id)){
            return new ResponseEntity(new Mensaje("The ID doesnt exist"), HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyectos = sProyectos.getOne(id).get();
        return new ResponseEntity(proyectos, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sProyectos.existsById(id)){
            return new ResponseEntity(new Mensaje("The ID doesnt exist"), HttpStatus.NOT_FOUND);
        }
        sProyectos.delete(id);
        return new ResponseEntity(new Mensaje("Deleted"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProyectos dtoProyectos){      
        if(StringUtils.isBlank(dtoProyectos.getNombreP()))
            return new ResponseEntity(new Mensaje("Field required"), HttpStatus.BAD_REQUEST);
        if(sProyectos.existsByNombreP(dtoProyectos.getNombreP()))
            return new ResponseEntity(new Mensaje("Already exists"), HttpStatus.BAD_REQUEST);
        
        Proyectos proyecto = new Proyectos();
        sProyectos.save(proyecto);
        
        return new ResponseEntity(new Mensaje("Added"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoProyectos dtoProyectos){
        if(!sProyectos.existsById(id)){
            return new ResponseEntity(new Mensaje("The ID doesnt exist"), HttpStatus.NOT_FOUND);
        }
        if(sProyectos.existsByNombreP(dtoProyectos.getNombreP()) && sProyectos.getByNombreP(dtoProyectos.getNombreP()).get().getId() != id){
            return new ResponseEntity(new Mensaje("The name already exists"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoProyectos.getNombreP())){
            return new ResponseEntity(new Mensaje("Field required"), HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyectos = sProyectos.getOne(id).get();
        
        proyectos.setNombreP(dtoProyectos.getNombreP());
        proyectos.setDescripcionP(dtoProyectos.getDescripcionP());
        
        sProyectos.save(proyectos);
        
        return new ResponseEntity(new Mensaje("Updated"), HttpStatus.OK);
    }
}
