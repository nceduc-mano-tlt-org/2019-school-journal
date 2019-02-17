package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.entity.Project;
import ru.nceduc.journal.repository.ProjectRepository;
import ru.nceduc.journal.service.GenericService;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectServiceImpl implements GenericService<Project> {

    private final ProjectRepository repository;

    @Override
    public void create(Project entity) {
        repository.save(entity);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public void patch(String id, Project entity) {
        Project actual = repository.findById(id).get();
        repository.deleteById(id);
        if(entity.getNameProject() != null){
            actual.setNameProject(entity.getNameProject());
        }
        if(entity.getModifiedDate() != null){
            actual.setModifiedDate(entity.getModifiedDate());
        }
        repository.save(actual);
    }

    @Override
    public void update(String id, Project entity) {
        repository.save(entity);
    }

    @Override
    public Project get(String id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Project> getAll() {
        List<Project> all = new ArrayList<>();
        repository.findAll().forEach(all::add);
        return all;
    }
}
