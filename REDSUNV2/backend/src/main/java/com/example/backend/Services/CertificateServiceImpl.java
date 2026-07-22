package com.example.backend.Services;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.Entities.Certificates;
import com.example.backend.Repositories.CertificateRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class CertificateServiceImpl implements CertificatesService {
    private final CertificateRepository certificateRepository;

    public CertificateServiceImpl(CertificateRepository certificateRepository){
        this.certificateRepository = certificateRepository;
    }

    @Override
    public Certificates saveCertificate(Certificates certificate){
        return certificateRepository.save(certificate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Certificates> getAllCertificates(){
        return certificateRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Certificates getCertificateById(UUID id){
        return certificateRepository.findById(id).orElseThrow(
             () -> new EntityNotFoundException("Certificate not found with this id: " + id)
        );
    }

    @Override
    public void deleteCertificate(UUID id){
        if(!certificateRepository.existsById(id)){
            throw new EntityNotFoundException("Delete Failed!, Certificate not found with given id: " + id);
        }

        certificateRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Certificates> searchCertificatesByName(String keyword){
        return certificateRepository.findByNameContainingOrderByIssueDateDesc(keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Certificates> searchCertificatesByIssuer(String keyword){
        return certificateRepository.findByIssuerContainingOrderByIssueDateDesc(keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Certificates> searchCertificatesByIssueDate(LocalDateTime date,boolean isAfterDate){
        return isAfterDate ? certificateRepository.findByIssueDateAfterThisDate(date) 
        : certificateRepository.findByIssueDateBeforeThisDate(date);
    }
}
