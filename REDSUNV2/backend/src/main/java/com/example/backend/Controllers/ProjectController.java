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

import com.example.backend.Entities.Projects;
import com.example.backend.Services.ProjectService;

@RestController
@RequestMapping("/api/v1/projects")
@CrossOrigin("*")
public class ProjectController {
    
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Projects> createProject(@RequestBody Projects project){
        Projects savedProject = projectService.saveProject(project);
        return new ResponseEntity<>(savedProject,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Projects>> getAllProjects(){
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projects> getProjectById(@PathVariable UUID id){
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID id){
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    } 

    @GetMapping("/search/title")
    public ResponseEntity<List<Projects>> searchProjectByTitle(@RequestParam String keyword){
        return ResponseEntity.ok(projectService.searchProjectByTitle(keyword));
    }
}
