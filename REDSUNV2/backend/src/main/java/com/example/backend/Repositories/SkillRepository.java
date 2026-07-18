package com.example.backend.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.Entities.Skills;

/**
 * Data Access Layer interface for managing {@link Skills} entities.
 * Handles derived queries for searching skills by name or category with chronological sorting.
 */
@Repository
public interface SkillRepository extends JpaRepository<Skills, UUID> {

    /**
     * Finds skills containing the given keyword in their name, sorted by creation date.
     * 
     * @param keyword The partial string to match against the skill name.
     * @return A list of matching Skills ordered from newest to oldest.
     */
    List<Skills> findByNameContainingIgnoreCaseOrderByCreatedAtDesc(String keyword);

    /**
     * Finds skills containing the given keyword in their category, sorted by creation date.
     * 
     * @param keyword The partial string to match against the skill category.
     * @return A list of matching Skills ordered from newest to oldest.
     */
    List<Skills> findByCategoryContainingIgnoreCaseOrderByCreatedAtDesc(String keyword);
}