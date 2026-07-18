package com.example.backend.Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Note: Removed the unused/colliding 'Pattern' and 'UrlValidator' imports from the top!

/**
 * Represents a professional qualification or certification earned by the developer.
 */
@Entity
@Table(name = "certificates")
@Getter
@Setter
@Builder
@NoArgsConstructor  // Necessary for JPA database row deserialization
@AllArgsConstructor // Necessary for Lombok's Builder generation
public class Certificates {
    
    /**
     * Unique identifier for the certificate entry.
     */
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id;

    /**
     * The legal display name of the credential (e.g., "AWS Certified Cloud Practitioner").
     */
    @Column(name = "name", nullable = false, updatable = true)
    private String name;

    /**
     * The issuing authority or institution (e.g., "Amazon Web Services", "Microsoft").
     */
    @Column(name = "issuer", updatable = true)
    private String issuer;

    /**
     * The exact date on which the credential was issued.
     */
    @Column(name = "issue_date", updatable = true)
    private LocalDate issueDate;

    /**
     * The verification or showcase link pointing directly to the certificate credential.
     * Stored as a simple String to prevent legacy java.net.URL performance bugs.
     */
    @Column(name = "url", updatable = true)
    private String url; // Clean storage field with NO conflicting annotation collisions!

    /**
     * Timestamp indicating when this certificate record was first saved.
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp indicating the last time any properties of this certificate were modified.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}