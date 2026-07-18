package com.example.backend.Services;

import java.util.List;
import java.util.UUID;

import com.example.backend.Entities.Skills;

/**
 * Service interface defining the business logic contract for managing {@link Skills}.
 * Acts as the intermediary layer between the REST controllers and data access repositories.
 */
public interface SkillsService {
    
    /**
     * Persists a new skill or updates an existing one in the system.
     * 
     * @param skill The skill entity to be saved.
     * @return The persisted skill entity including its generated UUID.
     */
    Skills saveSkill(Skills skill);

    /**
     * Retrieves all skills currently stored in the database.
     * 
     * @return A list of all skills.
     */
    List<Skills> getAllSkills();

    /**
     * Fetches a specific skill by its unique identifier.
     * 
     * @param id The UUID of the target skill.
     * @return The found skill entity.
     * @throws jakarta.persistence.EntityNotFoundException if no record matches the ID.
     */
    Skills getSkillById(UUID id);

    /**
     * Removes a skill from the system using its unique identifier.
     * 
     * @param id The UUID of the skill to be deleted.
     * @throws jakarta.persistence.EntityNotFoundException if the skill does not exist.
     */
    void deleteSkill(UUID id);

    /**
     * Searches for skills matching a partial keyword within their name.
     * Results are sorted chronologically from newest to oldest.
     * 
     * @param keyword The partial string text to search for.
     * @return A list of matching skills.
     */
    List<Skills> searchSkillByName(String keyword);

    /**
     * Searches for skills matching a partial keyword within their category.
     * Results are sorted chronologically from newest to oldest.
     * 
     * @param keyword The partial category text to search for.
     * @return A list of matching skills.
     */
    List<Skills> searchSkillByCategory(String keyword);
}