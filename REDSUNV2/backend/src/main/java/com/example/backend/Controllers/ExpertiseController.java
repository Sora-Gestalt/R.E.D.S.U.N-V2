package com.example.backend.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Entities.Expertise;
import com.example.backend.Services.ExpertiseService;

@RestController
@RequestMapping("/api/v1/expertise")
@CrossOrigin("*")
public class ExpertiseController {

    private final ExpertiseService expertiseService;

    public ExpertiseController(ExpertiseService expertiseService){
        this.expertiseService = expertiseService;
    }

    @PostMapping
    public ResponseEntity<Expertise> createExpertise(@RequestBody Expertise expertise){
        Expertise savedExpertise = expertiseService.saveExpertise(expertise);
        return new ResponseEntity<>(savedExpertise,HttpStatus.CREATED);
    }

    @GetMapping 
    public ResponseEntity<List<Expertise>> getAllExpertise(){
        return ResponseEntity.ok(expertiseService.getAllExpertise());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expertise> getExpertiseById(@PathVariable UUID id){
        return ResponseEntity.ok(expertiseService.getExpertiseById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpertise(@PathVariable UUID id){
        expertiseService.deleteExpertise(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<Expertise>> searchExpertiseByTitle(@RequestParam String keyword){
        return ResponseEntity.ok(expertiseService.searchByTitle(keyword));
    }

    @GetMapping("/search/company")
    public ResponseEntity<List<Expertise>> searchExpertiseByCompany(@RequestParam String keyword){
        return ResponseEntity.ok(expertiseService.searchByCompany(keyword));
    }
}
