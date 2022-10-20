package com.portfolio.Service;

import com.portfolio.Entity.Skills;
import com.portfolio.Repository.SkillsRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SkillsService {
    @Autowired
    SkillsRepository skillsRepository;
    
    public List<Skills> list(){
        return skillsRepository.findAll();
    }
    
    public Optional<Skills> getOne(int id){
        return skillsRepository.findById(id);
    }
    
    public Optional<Skills> getByNombre(String nombre){
        return skillsRepository.findByNombre(nombre);
    }
    
    public void save(Skills skill){
        skillsRepository.save(skill);
    }
    
    public void delete(int id){
        skillsRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return skillsRepository.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return skillsRepository.existsByNombre(nombre);
    }
}
