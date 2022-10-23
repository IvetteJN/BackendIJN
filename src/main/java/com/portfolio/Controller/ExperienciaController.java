package com.portfolio.Controller;

import com.portfolio.Dto.DtoExperiencia;
import com.portfolio.Entity.Experiencia;
import com.portfolio.Security.Controller.Mensaje;
import com.portfolio.Service.ExperienciaService;
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
@RequestMapping("/explab")
@CrossOrigin(origins = {"https://frontendijn.web.app","http://localhost:4200"})
public class ExperienciaController {
    @Autowired
    ExperienciaService sExperiencia;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = sExperiencia.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id")int id){
        if(!sExperiencia.existsById(id)){
            return new ResponseEntity(new Mensaje("The ID doesnt exist"), HttpStatus.BAD_REQUEST);
        }
        
        Experiencia experiencia = sExperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sExperiencia.existsById(id)){
            return new ResponseEntity(new Mensaje("The ID doesnt exist"), HttpStatus.NOT_FOUND);
        }
        sExperiencia.delete(id);
        return new ResponseEntity(new Mensaje("Deleted"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoExperiencia dtoexperiencia){      
        if(StringUtils.isBlank(dtoexperiencia.getNombreE()))
            return new ResponseEntity(new Mensaje("Name required"), HttpStatus.BAD_REQUEST);
        if(sExperiencia.existsByNombreE(dtoexperiencia.getNombreE()))
            return new ResponseEntity(new Mensaje("Already exists"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = new Experiencia(dtoexperiencia.getNombreE(), dtoexperiencia.getDescripcionE(), dtoexperiencia.getFechaFin(), dtoexperiencia.getFechaInicio());
        sExperiencia.save(experiencia);
        
        return new ResponseEntity(new Mensaje("Added"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoExperiencia dtoexperiencia){
        if(!sExperiencia.existsById(id)){
            return new ResponseEntity(new Mensaje("The ID doesnt exist"), HttpStatus.NOT_FOUND);
        }
        if(sExperiencia.existsByNombreE(dtoexperiencia.getNombreE()) && sExperiencia.getByNombreE(dtoexperiencia.getNombreE()).get().getId() != id){
            return new ResponseEntity(new Mensaje("That name already exists"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoexperiencia.getNombreE())){
            return new ResponseEntity(new Mensaje("Field required"), HttpStatus.BAD_REQUEST);
        }
        
        Experiencia experiencia = sExperiencia.getOne(id).get();
        
        experiencia.setNombreE(dtoexperiencia.getNombreE());
        experiencia.setDescripcionE(dtoexperiencia.getDescripcionE());
        experiencia.setFechaInicio(dtoexperiencia.getFechaInicio());
        experiencia.setFechaFin(dtoexperiencia.getFechaFin());
        
        sExperiencia.save(experiencia);
        
        return new ResponseEntity(new Mensaje("Updated"), HttpStatus.OK);
    }
}
