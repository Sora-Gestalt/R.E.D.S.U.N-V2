package com.example.backend.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backend.Entities.Expertise;

/**
 * Repository interface for managing {@link Expertise} entities.
 * Abstracts database interactions and leverages custom native queries for 
 * optimized many-to-many relationship fetches.
 */
@Repository
public interface ExpertiseRepository extends JpaRepository<Expertise, UUID> {

    /**
     * Searches for professional experiences where the title contains a specific phrase.
     * Generates a dynamic SQL WHERE clause using a case-sensitive 'LIKE' filter.
     * * @param keyword The partial text segment to look for inside the job title.
     * @return A List of matching Expertise records, or an empty list if no matches exist.
     */
    List<Expertise> findByTitleContaining(String keyword);

    /**
     * Searches for professional experiences at companies matching a given phrase.
     * * @param keyword The partial text segment to look for inside the company name.
     * @return A List of matching Expertise records, or an empty list if no matches exist.
     */
    List<Expertise> findByCompanyContaining(String keyword);

    /**
     * Executes a high-performance native join query to fetch all professional experiences
     * associated with a specific tech skill ID.
     * * <p><strong>Performance Note:</strong> Assumes a reverse index exists on the 
     * expertise_skills join table to ensure O(1) microsecond lookups from right-to-left.</p>
     * * @param skillId The unique UUID of the skill to filter experiences by.
     * @return A List of full Expertise records mapped to the provided skill, sorted by creation date.
     */
    @Query(value = "SELECT e.* FROM expertise e " +
                   "JOIN expertise_skills es ON e.id = es.expertise_id " +
                   "WHERE es.skill_id = :skillId " +
                   "ORDER BY e.created_at DESC", 
           nativeQuery = true
    )
    List<Expertise> findBySkills(@Param("skillId") UUID skillId);
}