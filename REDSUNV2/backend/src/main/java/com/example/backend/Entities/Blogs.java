package com.example.backend.Entities;

import java.time.Instant;
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
 * Represents a blog post entry in the application portfolio or CMS database.
 * <p>
 * This class maps directly to the "blogs" table and tracks titles, unique URL slugs, 
 * markdown/text contents, and publication timestamps.
 * </p>
 *
 * @author Backend Team
 * @version 1.0
 */
@Entity
@Table(name = "blogs")
@Getter
@Setter
@Builder
@NoArgsConstructor  // Necessary for Hibernate database retrieval operations
@AllArgsConstructor // Necessary for Lombok's Builder design pattern compilation
public class Blogs {

    /**
     * Unique identifier for the blog post.
     * Managed manually or assigned prior to persistence.
     */
    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private UUID id;

    /**
     * The headline or title of the blog post.
     */
    @Column(name = "title", nullable = false, updatable = true)
    private String title;

    /**
     * A unique, URL-friendly representation of the title (e.g., "my-first-post").
     * Used for routing and SEO optimization on the client application.
     */
    @Column(name = "slug", nullable = false, updatable = true, unique = true)
    private String slug;

    /**
     * The rich body content of the blog post. 
     * Stored typically as standard text, HTML, or Markdown.
     */
    @Column(name = "content", updatable = true)
    private String content;

    /**
     * The date and time when the blog post was officially published to the public.
     * <p>
     * <b>Best Practice Modernization:</b> Converted from {@code java.sql.Timestamp} 
     * to {@code java.time.Instant}. Using {@code Instant} represents a clean, UTC-aligned 
     * timestamp that translates smoothly across different database servers without local timezone drift.
     * </p>
     */
    @Column(name = "published_at", updatable = false)
    private Instant publishedAt;

    /**
     * Timestamp indicating when this blog record was first saved to the database.
     * Automatically captured by Hibernate.
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp indicating the last time any properties of this blog were modified.
     * Automatically updated by Hibernate.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}