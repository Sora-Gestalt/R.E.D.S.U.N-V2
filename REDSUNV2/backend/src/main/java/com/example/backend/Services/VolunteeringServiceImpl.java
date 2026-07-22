package com.example.backend.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.Entities.Volunteering;
import com.example.backend.Repositories.VolunteeringRepository;

import jakarta.persistence.EntityNotFoundException;

/**
 * Service implementation for managing {@link Volunteering} business logic and data persistence.
 * <p>
 * Configured with class-level transaction management where read operations are optimized
 * using {@code @Transactional(readOnly = true)}.
 */
@Service
@Transactional
public class VolunteeringServiceImpl implements VolunteeringService {

    private final VolunteeringRepository volunteeringRepository;

    /**
     * Constructs a new {@code VolunteeringServiceImpl} with required repository dependency.
     *
     * @param volunteeringRepository repository for executing {@link Volunteering} database operations
     */
    public VolunteeringServiceImpl(VolunteeringRepository volunteeringRepository) {
        this.volunteeringRepository = volunteeringRepository;
    }

    /**
     * Persists or updates a {@link Volunteering} record in the database.
     *
     * @param volunteering the volunteering entity object to save
     * @return the saved {@link Volunteering} entity
     */
    @Override
    public Volunteering saveVolunteering(Volunteering volunteering) {
        return volunteeringRepository.save(volunteering);
    }

    /**
     * Retrieves all volunteering entries from the database.
     *
     * @return a {@link List} of all {@link Volunteering} instances
     */
    @Override
    @Transactional(readOnly = true)
    public List<Volunteering> getAllVolunteerings() {
        return volunteeringRepository.findAll();
    }

    /**
     * Fetches a volunteering entry by its unique identifier.
     *
     * @param id the {@link UUID} primary key of the target volunteering record
     * @return the matching {@link Volunteering} entity
     * @throws EntityNotFoundException if no record matches the given UUID
     */
    @Override
    @Transactional(readOnly = true)
    public Volunteering getVolunteeringById(UUID id) {
        return volunteeringRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Volunteering not found with this id: " + id));
    }

    /**
     * Removes a volunteering record by its unique identifier after verifying existence.
     *
     * @param id the {@link UUID} primary key of the record to delete
     * @throws EntityNotFoundException if no record exists with the provided UUID
     */
    @Override
    public void deleteVolunteering(UUID id) {
        if (!volunteeringRepository.existsById(id)) {
            throw new EntityNotFoundException("Delete Failed!, Volunteering not found with given id: " + id);
        }

        volunteeringRepository.deleteById(id);
    }

    /**
     * Searches for volunteering records where the role matches or contains the keyword,
     * ordered by start date descending.
     *
     * @param keyword filter term for role matching
     * @return a {@link List} of matching {@link Volunteering} entities ordered by start date
     */
    @Override
    @Transactional(readOnly = true)
    public List<Volunteering> searchByRoleOrderedByStartDate(String keyword) {
        return volunteeringRepository.findByRoleContainingOrderByStartDateDesc(keyword);
    }

    /**
     * Searches for volunteering records where the role matches or contains the keyword,
     * ordered by end date descending.
     *
     * @param keyword filter term for role matching
     * @return a {@link List} of matching {@link Volunteering} entities ordered by end date
     */
    @Override
    @Transactional(readOnly = true)
    public List<Volunteering> searchByRoleOrderedByEndDate(String keyword) {
        return volunteeringRepository.findByRoleContainingOrderByEndDateDesc(keyword);
    }

    /**
     * Searches for volunteering records where the organization name matches or contains the keyword,
     * ordered by start date descending.
     *
     * @param keyword filter term for organization matching
     * @return a {@link List} of matching {@link Volunteering} entities ordered by start date
     */
    @Override
    @Transactional(readOnly = true)
    public List<Volunteering> searchByOrganizationOrderedByStartDate(String keyword) {
        return volunteeringRepository.findByOrganizationContainingOrderByStartDateDesc(keyword);
    }

    /**
     * Searches for volunteering records where the organization name matches or contains the keyword,
     * ordered by end date descending.
     *
     * @param keyword filter term for organization matching
     * @return a {@link List} of matching {@link Volunteering} entities ordered by end date
     */
    @Override
    @Transactional(readOnly = true)
    public List<Volunteering> searchByOrganizationOrderedByEndDate(String keyword) {
        return volunteeringRepository.findByOrganizationContainingOrderByEndDateDesc(keyword);
    }
}