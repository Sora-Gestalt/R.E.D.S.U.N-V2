package com.example.backend.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backend.Entities.Volunteering;

/**
 * Data Access Layer interface for managing {@link Volunteering} entities.
 * Offers flexible keyword filtering and native join queries structured around chronological sorting.
 */
@Repository
public interface VolunteeringRepository extends JpaRepository<Volunteering, UUID> {
    
    /**
     * Searches volunteer experiences by role description, sorted by start date from newest to oldest.
     * 
     * @param keyword The partial text to search within the role title or description.
     * @return A list of matching experiences ordered by descending start date.
     */
    List<Volunteering> findByRoleContainingOrderByStartDateDesc(String keyword);

    /**
     * Searches volunteer experiences by organization name, sorted by start date from newest to oldest.
     * 
     * @param keyword The partial text to search within the organization name.
     * @return A list of matching experiences ordered by descending start date.
     */
    List<Volunteering> findByOrganizationContainingOrderByStartDateDesc(String keyword);

    /**
     * Searches volunteer experiences by role description, sorted by conclusion date from newest to oldest.
     * 
     * @param keyword The partial text to search within the role title or description.
     * @return A list of matching experiences ordered by descending end date.
     */
    List<Volunteering> findByRoleContainingOrderByEndDateDesc(String keyword);

    /**
     * Searches volunteer experiences by organization name, sorted by conclusion date from newest to oldest.
     * 
     * @param keyword The partial text to search within the organization name.
     * @return A list of matching experiences ordered by descending end date.
     */
    List<Volunteering> findByOrganizationContainingOrderByEndDateDesc(String keyword);

    /**
     * Retrieves all volunteer experiences tied to a given skill ID, starting with the most recent.
     * 
     * @param skillId The unique UUID of the associated skill.
     * @return A list of volunteering entities ordered from newest to oldest start dates.
     */
    @Query(value = "SELECT v.* FROM volunteering v " +
                   "JOIN volunteering_skills vs ON v.id = vs.volunteering_id " +
                   "WHERE vs.skill_id = :skillId " + 
                   "ORDER BY v.start_date DESC", 
           nativeQuery = true
    )
    List<Volunteering> findBySkillsOrderByStartDateDesc(@Param("skillId") UUID skillId);

    /**
     * Retrieves all volunteer experiences tied to a given skill ID, starting with the oldest.
     * 
     * @param skillId The unique UUID of the associated skill.
     * @return A list of volunteering entities ordered from oldest to newest start dates.
     */
    @Query(value = "SELECT v.* FROM volunteering v " +
                   "JOIN volunteering_skills vs ON v.id = vs.volunteering_id " +
                   "WHERE vs.skill_id = :skillId " + 
                   "ORDER BY v.start_date ASC", 
           nativeQuery = true
    )
    List<Volunteering> findBySkillsOrderByStartDateAsc(@Param("skillId") UUID skillId);
}