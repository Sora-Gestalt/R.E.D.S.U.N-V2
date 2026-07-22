package com.example.backend.Services;

import java.util.List;
import java.util.UUID;

import com.example.backend.Entities.Expertise;

/**
 * Service interface defining the business contract for operations on {@link Expertise} entities.
 * <p>
 * Manages domain operations for professional expertise and work experience entries, including
 * persistence, lookup operations, removal, and keyword-based search functionality.
 */
public interface ExpertiseService {

    /**
     * Saves or updates an {@link Expertise} entity in the application storage.
     *
     * @param expertise the expertise entity object to be saved
     * @return the saved {@link Expertise} entity including database-generated values
     */
    Expertise saveExpertise(Expertise expertise);

    /**
     * Retrieves all expertise entries stored in the system.
     *
     * @return a {@link List} containing all {@link Expertise} entities
     */
    List<Expertise> getAllExpertise();

    /**
     * Retrieves a single expertise entry by its unique identifier.
     *
     * @param id the {@link UUID} primary key of the target expertise record
     * @return the matching {@link Expertise} entity
     * @throws jakarta.persistence.EntityNotFoundException if no entry matches the given UUID
     */
    Expertise getExpertiseById(UUID id);

    /**
     * Deletes an expertise entry from storage by its unique identifier.
     *
     * @param id the {@link UUID} primary key of the expertise record to remove
     * @throws jakarta.persistence.EntityNotFoundException if no entry matches the given UUID
     */
    void deleteExpertise(UUID id);

    /**
     * Searches for expertise records where the title contains or matches the specified keyword.
     *
     * @param keyword search term to filter by expertise title
     * @return a {@link List} of matching {@link Expertise} entities
     */
    List<Expertise> searchByTitle(String keyword);

    /**
     * Searches for expertise records where the company name contains or matches the specified keyword.
     *
     * @param keyword search term to filter by company name
     * @return a {@link List} of matching {@link Expertise} entities
     */
    List<Expertise> searchByCompany(String keyword);
}