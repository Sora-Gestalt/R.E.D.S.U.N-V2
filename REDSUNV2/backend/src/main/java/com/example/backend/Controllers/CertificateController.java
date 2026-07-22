package com.example.backend.Controllers;



import java.time.LocalDateTime;
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

import com.example.backend.Services.CertificatesService;
import com.example.backend.Entities.Certificates;;

@RestController
@RequestMapping("/api/v1/certificates")
@CrossOrigin("*")
public class CertificateController {
    
    private final CertificatesService certificatesService;

    public CertificateController(CertificatesService certificatesService){
        this.certificatesService = certificatesService;
    }

    @PostMapping
    public ResponseEntity<Certificates> createCertificate(@RequestBody Certificates certificate){
        Certificates savedCertificate = certificatesService.saveCertificate(certificate);
        return new ResponseEntity<>(savedCertificate,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Certificates>> getAllCertificates(){
        return ResponseEntity.ok(certificatesService.getAllCertificates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificates> getCertificateById(@PathVariable UUID id){
        return ResponseEntity.ok(certificatesService.getCertificateById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable UUID id){
        certificatesService.deleteCertificate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<Certificates>> searchCertificateByName(@RequestParam String keyword){
        return ResponseEntity.ok(certificatesService.searchCertificatesByName(keyword));
    }

    @GetMapping("/search/issuer")
    public ResponseEntity<List<Certificates>> searchCertificateByIssuer(@RequestParam String keyword){
        return ResponseEntity.ok(certificatesService.searchCertificatesByIssuer(keyword));
    }

    @GetMapping("/search/issuer/issue_date/after_date")
    public ResponseEntity<List<Certificates>> searchCertificateByIssueDateAfter(@RequestParam LocalDateTime date){
        return ResponseEntity.ok(certificatesService.searchCertificatesByIssueDate(date,true));
    }

    @GetMapping("/search/issuer/issue_date/before_date")
    public ResponseEntity<List<Certificates>> searchCertificateByIssueDateBefore(@RequestParam LocalDateTime date){
        return ResponseEntity.ok(certificatesService.searchCertificatesByIssueDate(date,false));
    }
}
