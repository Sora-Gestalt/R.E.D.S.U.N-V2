package com.example.backend.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.Entities.Projects;
import com.example.backend.Repositories.ProjectRepository;

import jakarta.persistence.EntityNotFoundException;

/**
 * Service implementation for managing {@link Projects} business operations and persistence logic.
 * <p>
 * Manages database interactions via {@link ProjectRepository} under explicit transaction boundaries,
 * optimizing read operations with {@code @Transactional(readOnly = true)}.
 */
@Service
@Transactional
public class ProjectsServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    /**
     * Constructs a new {@code ProjectsServiceImpl} with required repository dependency.
     *
     * @param projectRepository repository responsible for executing {@link Projects} database operations
     */
    public ProjectsServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * Persists or updates a {@link Projects} record in the database.
     *
     * @param project the project entity object to be saved
     * @return the persisted {@link Projects} entity
     */
    @Override
    public Projects saveProject(Projects project) {
        return projectRepository.save(project);
    }

    /**
     * Retrieves all project entries present in the database.
     *
     * @return a {@link List} containing all {@link Projects} entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Projects> getAllProjects() {
        return projectRepository.findAll();
    }

    /**
     * Retrieves a project record by its unique identifier.
     *
     * @param id the {@link UUID} primary key of the target project
     * @return the matching {@link Projects} entity
     * @throws EntityNotFoundException if no project exists with the provided UUID
     */
    @Override
    @Transactional(readOnly = true)
    public Projects getProjectById(UUID id) {
        return projectRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Project not found with this id: " + id)
        );
    }

    /**
     * Deletes a project record by its unique identifier after confirming existence.
     *
     * @param id the {@link UUID} primary key of the project to be removed
     * @throws EntityNotFoundException if no project exists with the provided UUID
     */
    @Override
    public void deleteProject(UUID id) {
        if (!projectRepository.existsById(id)) {
            throw new EntityNotFoundException("Delete Failed!, Project not found with given id: " + id);
        }

        projectRepository.deleteById(id);
    }

    /**
     * Searches for projects matching a title keyword, ordered by creation date descending.
     *
     * @param keyword search term to filter project titles
     * @return a {@link List} of matching {@link Projects} entities sorted chronologically by creation date
     */
    @Override
    @Transactional(readOnly = true)
    public List<Projects> searchProjectByTitle(String keyword) {
        return projectRepository.findByTitleContainingOrderByCreatedAtDesc(keyword);
    }
}