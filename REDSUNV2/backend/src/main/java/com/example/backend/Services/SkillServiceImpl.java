package com.example.backend.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.Entities.Skills;
import com.example.backend.Repositories.SkillRepository;

import jakarta.persistence.EntityNotFoundException;

/**
 * Concrete implementation of the {@link SkillsService} managing transaction boundaries 
 * and database interactions for the Skills domain.
 */
@Service
@Transactional
public class SkillServiceImpl implements SkillsService {
    
    private final SkillRepository skillRepository;

    /**
     * Initializes the service with constructor-based dependency injection.
     * 
     * @param skillRepository The data access layer repository for skills.
     */
    public SkillServiceImpl(SkillRepository skillRepository){
        this.skillRepository = skillRepository;
    }

    @Override
    public Skills saveSkill(Skills skill){
        return skillRepository.save(skill);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Skills> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Skills getSkillById(UUID id){
        return skillRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Skill not found with given id: " + id));
    }

    @Override
    public void deleteSkill(UUID id) {
        if(!skillRepository.existsById(id)) {
            throw new EntityNotFoundException("Delete Failed!, Skill not found with given id: " + id);
        }
        skillRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Skills> searchSkillByName(String keyword){
        return skillRepository.findByNameContainingIgnoreCaseOrderByCreatedAtDesc(keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Skills> searchSkillByCategory(String keyword){
        return skillRepository.findByCategoryContainingIgnoreCaseOrderByCreatedAtDesc(keyword);
    }
}