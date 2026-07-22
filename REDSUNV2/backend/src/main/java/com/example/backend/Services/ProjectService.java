package com.example.backend.Services;

import java.util.List;
import java.util.UUID;

import com.example.backend.Entities.Projects;

/**
 * Service interface defining the business contract for operations on {@link Projects} entities.
 * <p>
 * Provides methods for creating, retrieving, updating, deleting, 
 * and searching project records within the application.
 */
public interface ProjectService {

    /**
     * Saves or updates a {@link Projects} record in the database.
     *
     * @param project the project entity object to be saved
     * @return the saved {@link Projects} entity including database-generated values (e.g., ID, timestamps)
     */
    Projects saveProject(Projects project);

    /**
     * Retrieves all project entries stored in the database.
     *
     * @return a {@link List} containing all {@link Projects} entities
     */
    List<Projects> getAllProjects();

    /**
     * Retrieves a specific project entry by its unique identifier.
     *
     * @param id the {@link UUID} primary key of the target project
     * @return the found {@link Projects} entity
     * @throws jakarta.persistence.EntityNotFoundException if no project exists with the given ID
     */
    Projects getProjectById(UUID id);

    /**
     * Removes a project entry from the database by its unique identifier.
     *
     * @param id the {@link UUID} primary key of the project to be deleted
     * @throws jakarta.persistence.EntityNotFoundException if no project exists with the given ID
     */
    void deleteProject(UUID id);

    /**
     * Searches for projects whose titles contain or match the specified keyword.
     *
     * @param keyword search term to filter projects by title
     * @return a {@link List} of matching {@link Projects} entities
     */
    List<Projects> searchProjectByTitle(String keyword);
}