package com.example.backend.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.example.backend.Entities.Certificates;

public interface CertificatesService {
    Certificates saveCertificate(Certificates certificate);

    List<Certificates> getAllCertificates();

    Certificates getCertificateById(UUID id);

    void deleteCertificate(UUID id);

    List<Certificates> searchCertificatesByName(String keyword);

    List<Certificates> searchCertificatesByIssuer(String keyword);

    List<Certificates> searchCertificatesByIssueDate(LocalDateTime date,boolean isAfterDate);
}
