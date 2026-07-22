package com.example.backend.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.Entities.Expertise;
import com.example.backend.Repositories.ExpertiseRepository;

import jakarta.persistence.EntityNotFoundException;

/**
 * Service implementation for managing {@link Expertise} business operations and persistence logic.
 * <p>
 * Handles operations against {@link ExpertiseRepository} under transaction boundaries,
 * applying {@code @Transactional(readOnly = true)} for performance optimization on query operations.
 */
@Service
@Transactional
public class ExpertiseServiceImpl implements ExpertiseService {

    private final ExpertiseRepository expertiseRepository;

    /**
     * Constructs a new {@code ExpertiseServiceImpl} with required repository dependency.
     *
     * @param expertiseRepository repository responsible for executing {@link Expertise} database operations
     */
    public ExpertiseServiceImpl(ExpertiseRepository expertiseRepository) {
        this.expertiseRepository = expertiseRepository;
    }

    /**
     * Persists or updates an {@link Expertise} entity in the database.
     *
     * @param expertise the expertise entity object to be saved
     * @return the persisted {@link Expertise} entity
     */
    @Override
    public Expertise saveExpertise(Expertise expertise) {
        return expertiseRepository.save(expertise);
    }

    /**
     * Retrieves all expertise entries present in the database.
     *
     * @return a {@link List} containing all {@link Expertise} entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Expertise> getAllExpertise() {
        return expertiseRepository.findAll();
    }

    /**
     * Retrieves an expertise record by its unique identifier.
     *
     * @param id the {@link UUID} primary key of the target expertise record
     * @return the matching {@link Expertise} entity
     * @throws EntityNotFoundException if no record matches the provided UUID
     */
    @Override
    @Transactional(readOnly = true)
    public Expertise getExpertiseById(UUID id) {
        return expertiseRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Expertise not found with this id: " + id)
        );
    }

    /**
     * Removes an expertise record by its unique identifier after confirming existence.
     *
     * @param id the {@link UUID} primary key of the expertise record to remove
     * @throws EntityNotFoundException if no expertise record exists with the provided UUID
     */
    @Override
    public void deleteExpertise(UUID id) {
        if (!expertiseRepository.existsById(id)) {
            throw new EntityNotFoundException("Delete Failed!, Expertise not found with given id: " + id);
        }

        expertiseRepository.deleteById(id);
    }

    /**
     * Searches for expertise records where the title contains the specified keyword.
     *
     * @param keyword filter term to match against expertise titles
     * @return a {@link List} of matching {@link Expertise} entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Expertise> searchByTitle(String keyword) {
        return expertiseRepository.findByTitleContaining(keyword);
    }

    /**
     * Searches for expertise records where the company name contains the specified keyword.
     *
     * @param keyword filter term to match against company names
     * @return a {@link List} of matching {@link Expertise} entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Expertise> searchByCompany(String keyword) {
        return expertiseRepository.findByCompanyContaining(keyword);
    }
}