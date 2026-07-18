package com.example.backend.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backend.Entities.Projects;

/**
 * Data Access Layer interface for managing {@link Projects} entities.
 * Leverages Spring Data JPA derived query methods and optimized native SQL projections.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Projects, UUID> {
    
    /**
     * Performs a text-search on project titles and returns matching records 
     * ordered by their creation timestamp.
     * 
     * @param keyword The partial string to match inside the project titles.
     * @return A List of Projects containing the keyword, sorted from newest to oldest.
     */
    List<Projects> findByTitleContainingOrderByCreatedAtDesc(String keyword);

    /**
     * Executes an optimized native query to retrieve only the titles of projects 
     * associated with a given skill ID.
     * 
     * <p><strong>Performance Note:</strong> This partial projection avoids loading full 
     * heavy project entities into the persistence context, reducing database I/O.</p>
     * 
     * @param skillId The unique UUID of the skill to filter projects by.
     * @return A List of project titles matching the given skill, sorted from newest to oldest.
     */
    @Query(value = "SELECT p.title as project_title FROM projects p " + 
                   "JOIN project_skills ps ON p.id = ps.project_id " + 
                   "WHERE ps.skill_id = :skillId " +
                   "ORDER BY p.created_at DESC", 
           nativeQuery = true
    )
    List<String> findBySkills(@Param("skillId") UUID skillId);
}