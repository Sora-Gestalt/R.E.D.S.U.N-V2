package com.example.backend.Repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backend.Entities.Certificates;

/**
 * Data Access Object (DAO) interface managing database operations for {@link Certificates} entities.
 * Features derived lookup keywords and hand-optimized native query boundary limits for PostgreSQL.
 */
@Repository
public interface CertificateRepository extends JpaRepository<Certificates, UUID> {

    /**
     * Filters certificates whose names contain a specific search string keyword sequence.
     * Maps to a case-sensitive SQL LIKE filter under the hood.
     *
     * @param keyword Text fragment to search inside certificate names.
     * @return List of matching certificates sorted by issue date in descending order.
     */
    List<Certificates> findByNameContainingOrderByIssueDateDesc(String keyword); // Fixed CamelCase 'I'

    /**
     * Filters certificates whose issuer field contains a specific search string keyword sequence.
     *
     * @param keyword Text fragment to search inside issuers.
     * @return List of matching certificates sorted by issue date in descending order.
     */
    List<Certificates> findByIssuerContainingOrderByIssueDateDesc(String keyword); // Fixed CamelCase 'I'

    /**
     * Fetches all records issued strictly after a specified point in time.
     * * @param issueDate The threshold cutoff timestamp.
     * @return List of newly achieved credentials ordered by relevance.
     */
    @Query(value = "SELECT * FROM certificates c WHERE c.name IS NOT NULL AND c.issue_date > :issueDate ORDER BY c.issue_date DESC", 
           nativeQuery = true) // Added missing nativeQuery tag
    List<Certificates> findByIssueDateAfterThisDate(@Param("issueDate") LocalDateTime issueDate); // Fixed name typo

    /**
     * Fetches historical legacy records issued strictly prior to a specified point in time.
     * * @param issueDate The maximum age boundary timestamp.
     * @return List of older credentials ordered by timeline.
     */
    @Query(value = "SELECT * FROM certificates c WHERE c.name IS NOT NULL AND c.issue_date < :issueDate ORDER BY c.issue_date DESC", 
           nativeQuery = true) // Added missing nativeQuery tag
    List<Certificates> findByIssueDateBeforeThisDate(@Param("issueDate") LocalDateTime issueDate); // Fixed name typo

    /**
     * High-performance relational join pulling only naming metadata for certificates linked 
     * to a target technical skill ID.
     * * <p><strong>Optimization Note:</strong> Employs projection interface data hydration 
     * which drops the memory tracking cost of the entity cache manager layer entirely.</p>
     * * @param skillId The unique identifier of the technical skill.
     * @return A list of lightweight summary projections sorted sequentially by completion timeline.
     */
    @Query(value = "SELECT c.name AS name FROM certificates c " + // Added trailing space fix
                   "JOIN certificate_skills cs ON c.id = cs.certificate_id " +        // Added trailing space fix
                   "WHERE cs.skill_id = :skillId " +
                   "ORDER BY c.issue_date DESC", nativeQuery = true)
    List<String> findCertificateBySkills(@Param("skillId") UUID skillId); // Converted String to Projection
}