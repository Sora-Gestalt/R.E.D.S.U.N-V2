package com.example.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.backend.Entities.Blogs;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Data Access Object (DAO) layer for managing {@link Blogs} entities.
 * Extends {@link JpaRepository} to inherit default CRUD operations.
 * * Includes optimizations for partial projections (slug fetching) and custom native 
 * SQL queries fine-tuned for a PostgreSQL backend.
 */
@Repository
public interface BlogRepository extends JpaRepository<Blogs, UUID> {

    /**
     * Looks up a specific blog post by its unique URL slug.
     * Utilizes Spring Data derived query methods to enforce cardinally safe lookups.
     * * @param slug The unique string identifier used in client routing.
     * @return An {@link Optional} containing the blog entity if found, or empty if it does not exist.
     */
    Optional<Blogs> findBySlug(String slug);

    /**
     * Searches for blog posts whose titles contain the specified keyword sequence.
     * * @param title The keyword or text fragment to find within blog titles.
     * @return A {@link List} of matching blogs. Returns an empty list if no titles match.
     */
    List<Blogs> findByTitleContaining(String title);

    /**
     * Retrieves all published blog posts that have a publication date strictly *after* * the provided timestamp.
     * * <p><strong>Database Execution:</strong> Executes a native PostgreSQL B-Tree index scan 
     * over the `published_at` column.</p>
     * * @param date The starting threshold timestamp.
     * @return A {@link List} of published blogs ordered from most recent to oldest.
     */
    @Query(value = "SELECT * FROM blogs b WHERE b.published_at IS NOT NULL AND b.published_at > :date ORDER BY b.published_at DESC", 
       nativeQuery = true)
    List<Blogs> findByDateAfterThisDate(@Param("date") LocalDateTime date);

    /**
     * Retrieves historical published blog posts that have a publication date strictly *before* * the provided timestamp.
     * * <p><strong>Database Execution:</strong> Executes a native PostgreSQL B-Tree index scan 
     * over the `published_at` column.</p>
     * * @param date The ending threshold timestamp.
     * @return A {@link List} of older published blogs ordered from closest to that date backwards.
     */
    @Query(value = "SELECT * FROM blogs b WHERE b.published_at IS NOT NULL AND b.published_at < :date ORDER BY b.published_at DESC", 
       nativeQuery = true)
    List<Blogs> findByDateBeforeThisDate(@Param("date") LocalDateTime date);

    /**
     * High-performance keyword/skill filtering that isolates and returns only the URL slugs 
     * of blogs matching a specific technical skill.
     * * <p><strong>Performance Optimization Strategy (Partial Fetching / Projection):</strong> 
     * Avoids loading large text columns (such as the blog `content` body) and mapping full 
     * Hibernate entities into JVM memory. It leverages an explicit inner join directly over 
     * the `blog_skills` associative table to drastically cut down database I/O and payload size.</p>
     * * <p><em>Note: Ensure a secondary reverse index exists on `blog_skills(skill_id)` in 
     * PostgreSQL to prevent a full-table scan on the join bridge.</em></p>
     * * @param skillId The {@link UUID} of the technical skill filtering the posts.
     * @return A {@link List} of unique blog slug strings, sorted by their creation date.
     */
    @Query(value = "SELECT b.slug AS slug FROM blogs b " + 
        "JOIN blog_skills bs ON b.id = bs.blog_id " + 
        "WHERE bs.skill_id = :skillId " +
        "ORDER BY b.created_at DESC", nativeQuery = true)
    List<String> findBySkills(@Param("skillId") UUID skillId);
}