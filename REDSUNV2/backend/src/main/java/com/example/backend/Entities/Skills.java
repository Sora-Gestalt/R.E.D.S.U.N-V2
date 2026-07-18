package com.example.backend.Entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a professional or technical skill within the application portfolio.
 * <p>
 * This class maps directly to the "skills" table in the database and is used 
 * to classify the developer's strengths (e.g., Frontend, Backend, DevOps).
 * </p>
 * * @author Backend Team
 * @version 1.0
 */
@Entity
@Table(name = "skills")
@Getter
@Setter
@Builder
@NoArgsConstructor  // Necessary for JPA database-to-object mapping
@AllArgsConstructor // Necessary for Lombok's Builder pattern
public class Skills {
    
    /**
     * Unique identifier for the skill record.
     * Managed manually or populated via Java logic prior to persistence.
     */
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id;

    /**
     * The name of the skill (e.g., "Java", "React", "Docker").
     * <p>
     * <b>Best Practice Hint:</b> Depending on your application's requirements, 
     * you might want to add {@code unique = true} to this column to prevent 
     * duplicate skill names from being saved to your database.
     * </p>
     */
    @Column(name = "name", nullable = false, updatable = true)
    private String name;

    /**
     * The categorization of the skill (e.g., "Language", "Framework", "Database", "Tool").
     * Helps group and filter skills dynamically on your client-side UI.
     */
    @Column(name = "category", updatable = true)
    private String category;

    /**
     * Timestamp indicating when this skill was registered.
     * Automatically captured by Hibernate upon initial insertion.
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp indicating the last time any property of this skill was modified.
     * Automatically recalculated by Hibernate on every save/update event.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}