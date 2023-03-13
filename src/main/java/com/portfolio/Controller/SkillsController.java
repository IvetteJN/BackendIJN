package com.portfolio.Controller;

import com.portfolio.Dto.DtoSkills;
import com.portfolio.Entity.Skills;
import com.portfolio.Security.Controller.Mensaje;
import com.portfolio.Service.SkillsService;
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
@CrossOrigin(origins = "http://portfolio-ivette-jael-no-a42d5.web.app")
@RequestMapping("/skill")
public class SkillsController {
    @Autowired
    SkillsService skillsService;

    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list() {
        List<Skills> list = skillsService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") int id) {
        if (!skillsService.existsById(id)) {
            return new ResponseEntity(new Mensaje("The ID doesnt exist"), HttpStatus.NOT_FOUND);
        }
        Skills skills = skillsService.getOne(id).get();
        return new ResponseEntity(skills, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!skillsService.existsById(id)) {
            return new ResponseEntity(new Mensaje("The ID doesnt exist"), HttpStatus.NOT_FOUND);
        }
        skillsService.delete(id);
        return new ResponseEntity(new Mensaje("Deleted"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoSkills dtoskills) {
        if (StringUtils.isBlank(dtoskills.getNombre())) {
            return new ResponseEntity(new Mensaje("Name required"), HttpStatus.BAD_REQUEST);
        }
        if (skillsService.existsByNombre(dtoskills.getNombre())) {
            return new ResponseEntity(new Mensaje("Already exists"), HttpStatus.BAD_REQUEST);
        }

        Skills skills = new Skills(dtoskills.getNombre(), dtoskills.getPorcentaje());
        skillsService.save(skills);

        return new ResponseEntity(new Mensaje("Added"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoSkills dtoskills) {
        
        if (!skillsService.existsById(id)) {
            return new ResponseEntity(new Mensaje("The ID doesnt exist"), HttpStatus.BAD_REQUEST);
        }
        
        if (skillsService.existsByNombre(dtoskills.getNombre()) && skillsService.getByNombre(dtoskills.getNombre()).get()
                .getId() != id) {
            return new ResponseEntity(new Mensaje("That name already exists"), HttpStatus.BAD_REQUEST);
        }
        
        if (StringUtils.isBlank(dtoskills.getNombre())) {
            return new ResponseEntity(new Mensaje("Field required"), HttpStatus.BAD_REQUEST);
        }

        Skills skills = skillsService.getOne(id).get();
        skills.setNombre(dtoskills.getNombre());
        skills.setPorcentaje(dtoskills.getPorcentaje());

        skillsService.save(skills);
        return new ResponseEntity(new Mensaje("Updated"), HttpStatus.OK);

    }
}
