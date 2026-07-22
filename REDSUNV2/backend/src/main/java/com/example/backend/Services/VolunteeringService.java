package com.example.backend.Services;

import java.util.List;
import java.util.UUID;

import com.example.backend.Entities.Volunteering;

/**
 * Service interface defining business contract for operations on {@link Volunteering} entities.
 * <p>
 * Encapsulates management actions such as saving, retrieving, deleting, 
 * and performing sorted search operations on volunteer experience records.
 */
public interface VolunteeringService {

    /**
     * Saves or updates a {@link Volunteering} entity in the system.
     *
     * @param volunteering the volunteering entity object to be saved
     * @return the persisted {@link Volunteering} entity with populated database properties (e.g., ID, timestamps)
     */
    Volunteering saveVolunteering(Volunteering volunteering);

    /**
     * Retrieves all volunteering records present in the database.
     *
     * @return a {@link List} of all {@link Volunteering} instances
     */
    List<Volunteering> getAllVolunteerings();

    /**
     * Fetches a single volunteering record by its unique identifier.
     *
     * @param id the {@link UUID} primary key of the target volunteering record
     * @return the found {@link Volunteering} entity
     * @throws RuntimeException (or custom ResourceNotFoundException) if no entity matches the provided ID
     */
    Volunteering getVolunteeringById(UUID id);

    /**
     * Deletes a volunteering record from the database by its unique identifier.
     *
     * @param id the {@link UUID} primary key of the record to be removed
     */
    void deleteVolunteering(UUID id);

    /**
     * Searches for volunteering records where the role matches or contains the specified keyword,
     * sorted in ascending/descending order by start date.
     *
     * @param keyword search term to filter by role
     * @return a {@link List} of matching {@link Volunteering} entities ordered by start date
     */
    List<Volunteering> searchByRoleOrderedByStartDate(String keyword);

    /**
     * Searches for volunteering records where the role matches or contains the specified keyword,
     * sorted in ascending/descending order by end date.
     *
     * @param keyword search term to filter by role
     * @return a {@link List} of matching {@link Volunteering} entities ordered by end date
     */
    List<Volunteering> searchByRoleOrderedByEndDate(String keyword);

    /**
     * Searches for volunteering records where the organization name matches or contains the specified keyword,
     * sorted in ascending/descending order by start date.
     *
     * @param keyword search term to filter by organization
     * @return a {@link List} of matching {@link Volunteering} entities ordered by start date
     */
    List<Volunteering> searchByOrganizationOrderedByStartDate(String keyword);

    /**
     * Searches for volunteering records where the organization name matches or contains the specified keyword,
     * sorted in ascending/descending order by end date.
     *
     * @param keyword search term to filter by organization
     * @return a {@link List} of matching {@link Volunteering} entities ordered by end date
     */
    List<Volunteering> searchByOrganizationOrderedByEndDate(String keyword);
}