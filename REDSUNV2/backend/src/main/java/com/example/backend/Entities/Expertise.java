package com.example.backend.Entities;

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

/**
 * Represents professional expertise or work experience in the developer's portfolio.
 * <p>
 * This class maps directly to the "expertise" table in the database and captures
 * employment roles, companies worked for, and relevant duty/project descriptions.
 * </p>
 *
 * @author Backend Team
 * @version 1.0
 */
@Entity
@Table(name = "expertise")
@Getter
@Setter
@Builder
@NoArgsConstructor  // Crucial for Hibernate dynamic instantiation on database reads
@AllArgsConstructor // Crucial for Lombok's Builder pattern compilation
public class Expertise {

    /**
     * Unique identifier for the expertise entry.
     * Managed manually or assigned programmatically prior to persistence.
     */
    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private UUID id;

    /**
     * The job title or role held (e.g., "Senior Software Engineer", "Tech Lead").
     * This field is mandatory.
     */
    @Column(name = "title", nullable = false, updatable = true)
    private String title;

    /**
     * The name of the organization, company, or institution where this expertise was demonstrated.
     */
    @Column(name = "company", updatable = true)
    private String company;

    /**
     * A detailed description of the responsibilities, technologies utilized, 
     * or accomplishments achieved in this role.
     */
    @Column(name = "description", updatable = true)
    private String description;

    /**
     * Timestamp indicating when this work record was first registered.
     * Automatically captured by Hibernate.
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp indicating the last time this experience entry was updated.
     * Automatically updated by Hibernate on any modifications.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}