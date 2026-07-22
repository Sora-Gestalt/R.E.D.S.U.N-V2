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

import com.example.backend.Entities.Volunteering;
import com.example.backend.Services.VolunteeringService;

@RestController
@RequestMapping("/api/v1/volunteering")
@CrossOrigin("*")
public class VolunteeringController {
    private final VolunteeringService volunteeringService;

    public VolunteeringController(VolunteeringService volunteeringService){
        this.volunteeringService = volunteeringService;
    }

    @PostMapping
    public ResponseEntity<Volunteering> createVolunteering(@RequestBody Volunteering volunteering){
        Volunteering savedVolunteering = volunteeringService.saveVolunteering(volunteering);
        return new ResponseEntity<>(savedVolunteering,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Volunteering>> getAllVolunteerings(){
        return ResponseEntity.ok(volunteeringService.getAllVolunteerings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Volunteering> getVolunteeringById(@PathVariable UUID id){
        return ResponseEntity.ok(volunteeringService.getVolunteeringById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolunteering(@PathVariable UUID id){
        volunteeringService.deleteVolunteering(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/role/start_date")
    public ResponseEntity<List<Volunteering>> searchVolunteeringByRoleByStartDate(@RequestParam String keyword){
        return ResponseEntity.ok(volunteeringService.searchByRoleOrderedByStartDate(keyword));
    }

    @GetMapping("/search/role/end_date")
    public ResponseEntity<List<Volunteering>> searchVolunteeringByRoleByEndDate(@RequestParam String keyword){
        return ResponseEntity.ok(volunteeringService.searchByRoleOrderedByEndDate(keyword));
    }

    @GetMapping("/search/organization/start_date")
    public ResponseEntity<List<Volunteering>> searchVolunteeringByOrganizationByStartDate(@RequestParam String keyword){
        return ResponseEntity.ok(volunteeringService.searchByOrganizationOrderedByStartDate(keyword));
    }

    @GetMapping("/search/organization/end_date")
    public ResponseEntity<List<Volunteering>> searchVolunteeringByOrganizationByEndDate(@RequestParam String keyword){
        return ResponseEntity.ok(volunteeringService.searchByOrganizationOrderedByEndDate(keyword));
    }
}
