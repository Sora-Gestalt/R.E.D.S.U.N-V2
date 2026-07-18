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

/**
 * Represents a volunteering or community service experience in the developer's portfolio.
 * <p>
 * This class maps directly to the "volunteering" table in the database and preserves 
 * historical data regarding roles, organizations, and timeline ranges.
 * </p>
 * * @author Backend Team
 * @version 1.0
 */
@Entity
@Table(name = "volunteering")
@Getter
@Setter
@Builder
@NoArgsConstructor  // Necessary for Hibernate retrieval operations
@AllArgsConstructor // Necessary for Builder generation
public class Volunteering {

    /**
     * Unique identifier for the volunteering record.
     * Managed manually or assigned prior to persistence.
     */
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id;

    /**
     * The volunteer role or position title (e.g., "Lead Mentor", "Event Volunteer").
     */
    @Column(name = "role", nullable = false, updatable = true)
    private String role;

    /**
     * The name of the organization or charity host.
     */
    @Column(name = "organization", nullable = false, updatable = true)
    private String organization;

    /**
     * The date on which the volunteering involvement commenced.
     * <p>
     * <b>Best Practice Modernization:</b> Converted from {@code java.sql.Date} 
     * to {@code java.time.LocalDate}. The modern Java Time API integrates cleanly with JPA 
     * and avoids complex legacy time-zone translation bugs.
     * </p>
     */
    @Column(name = "start_date", updatable = true)
    private LocalDate startDate; // Fixed spelling from 'starDate' to 'startDate'

    /**
     * The date on which the volunteering involvement concluded.
     * Can be null if the position is currently active.
     */
    @Column(name = "end_date", updatable = true)
    private LocalDate endDate;

    /**
     * Timestamp indicating when this entry was added to the database.
     * Automatically captured by Hibernate.
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp indicating when any details in this record were last modified.
     * Automatically updated by Hibernate.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}