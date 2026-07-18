package com.example.backend.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; // Added missing import
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Entities.Skills;
import com.example.backend.Services.SkillsService;

/**
 * REST Controller exposing end-points for {@link Skills} operations.
 * Maps inbound HTTP traffic directly to the backend business service layers.
 */
@RestController
@RequestMapping("/api/v1/skills")
@CrossOrigin(origins = "*")
public class SkillController {

    private final SkillsService skillsService;

    /**
     * Initializes the controller with constructor-based dependency injection.
     * * @param skillsService The service contract layer handling domain logic.
     */
    public SkillController(SkillsService skillsService){
        this.skillsService = skillsService;
    }

    /**
     * Creates and registers a new skill inside the portfolio application database.
     * * @param skill The skill object deserialized from the HTTP request body.
     * @return A ResponseEntity enclosing the created Skill entity and an HTTP 201 Created status.
     */
    @PostMapping
    public ResponseEntity<Skills> createSkill(@RequestBody Skills skill){
        Skills savedSkill = skillsService.saveSkill(skill);
        return new ResponseEntity<>(savedSkill, HttpStatus.CREATED);
    }

    /**
     * Retrieves all skills currently tracked by the system.
     * * @return A list of all recorded skills alongside an HTTP 200 OK status.
     */
    @GetMapping
    public ResponseEntity<List<Skills>> getAllSkills(){
        return ResponseEntity.ok(skillsService.getAllSkills());
    }

    /**
     * Fetches a single skill profile using its unique UUID identifier.
     * * @param id The unique identifier parsed from the endpoint URI.
     * @return The requested skill instance matched with an HTTP 200 OK status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Skills> getSkillById(@PathVariable UUID id){ // Added @PathVariable
        return ResponseEntity.ok(skillsService.getSkillById(id));
    }

    /**
     * Completely removes a skill registration profile out of the system base.
     * * @param id The unique identifier parsed from the endpoint URI.
     * @return An empty HTTP 204 No Content response confirming removal success.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable UUID id){ // Added @PathVariable
        skillsService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Searches dynamically for skills matching a name phrase.
     * * @param keyword URL string parameter defining the filter criteria.
     * @return Chronologically sorted skill arrays matching the query parameter.
     */
    @GetMapping("/search/name")
    public ResponseEntity<List<Skills>> searchSkillByName(@RequestParam String keyword){
        return ResponseEntity.ok(skillsService.searchSkillByName(keyword));
    }

    /**
     * Searches dynamically for skills matching a structural category.
     * * @param keyword URL string parameter defining the category filter criteria.
     * @return Chronologically sorted skill arrays matching the query parameter.
     */
    @GetMapping("/search/category")
    public ResponseEntity<List<Skills>> searchSkillByCategory(@RequestParam String keyword){
        return ResponseEntity.ok(skillsService.searchSkillByCategory(keyword));
    }
}