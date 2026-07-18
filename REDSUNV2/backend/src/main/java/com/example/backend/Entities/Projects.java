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
 * Represents a developer project within the application portfolio.
 */
@Entity // Tells Spring to map this class to a database table [cite: 4]
@Table(name = "projects")
@Getter // Generates getters for all fields [cite: 36]
@Setter // Generates setters for all fields [cite: 36]
@Builder // Generates the fluid builder design pattern API
@NoArgsConstructor // Required by JPA to fetch/reconstruct objects [cite: 35]
@AllArgsConstructor // Required by @Builder to operate correctly
public class Projects {

    /**
     * Unique identifier for the project.
     */
    @Id // Marks the id field as the primary key [cite: 5]
    @Column(name = "id", nullable = false, updatable = false, unique = true) // Customizes exact DB rules [cite: 6]
    private UUID id;

    /**
     * The display title of the project.
     */
    @Column(name = "title", nullable = false, updatable = true)
    private String title;

    /**
     * A detailed explanation or summary of the project.
     */
    @Column(name = "description", nullable = true, updatable = true)
    private String description;

    /**
     * The live deployment or repository link to showcase the project.
     */
    @Column(name = "demo_link", nullable = true, updatable = true)
    private String demo_link;

    /**
     * Timestamp indicating when the project record was first persisted.
     */
    @CreationTimestamp // Automatically manages creation timestamp without manual SQL [cite: 25, 31]
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Preferred modern java.time date type [cite: 33]

    /**
     * Timestamp indicating the last time the project was updated.
     */
    @UpdateTimestamp // Automatically updates the timestamp on every change [cite: 25, 31]
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Preferred modern java.time date type [cite: 33]
}