
package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.ProjectDTO;
import ru.nceduc.journal.entity.Project;
import ru.nceduc.journal.repository.ProjectRepository;
import ru.nceduc.journal.service.GenericService;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectServiceImpl implements GenericService<ProjectDTO> {

    private final ProjectRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    private Project project = null;

    @Override
    public ProjectDTO create(ProjectDTO entity) {
        project = modelMapper.map(entity,Project.class);
        repository.save(project);
        if(repository.existsById(entity.getId())){
            return entity;
        }
        return null;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public ProjectDTO patch(ProjectDTO entity) {
        project.setId(entity.getId());
        project = repository.getOne(entity.getId());
        project = modelMapper.map(entity,Project.class);
        repository.save(project);
        return entity;
    }

    @Override
    public ProjectDTO update(ProjectDTO entity) {
        modelMapper.getConfiguration().setSkipNullEnabled(false);
        project = modelMapper.map(entity,Project.class);
        repository.save(project);
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        return entity;
    }

    @Override
    public ProjectDTO get(String id) {
        project = repository.getOne(id);
        ProjectDTO projectDTO = modelMapper.map(project,ProjectDTO.class);
        return projectDTO;
    }

    @Override
    public List<ProjectDTO> getAll() {
        ProjectDTO projectDTO;
        List<ProjectDTO> all = new ArrayList<>();
        for(Project project: repository.findAll()){
            projectDTO = modelMapper.map(project, ProjectDTO.class);
            all.add(projectDTO);
        }
        return all;
    }
}

