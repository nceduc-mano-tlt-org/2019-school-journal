
package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.ProjectDTO;
import ru.nceduc.journal.entity.Project;
import ru.nceduc.journal.repository.ProjectRepository;
import ru.nceduc.journal.service.ProjectService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;

    @Override
    public ProjectDTO create(ProjectDTO entity) {
        if (entity != null) {
            Project project = modelMapper.map(entity, Project.class);
            repository.save(project);
            return modelMapper.map(project, ProjectDTO.class);
        } else
            return null;
    }

    @Override
    public void delete(String id) {
        if (id != null && repository.existsById(id))
        repository.deleteById(id);
    }

    @Override
    public ProjectDTO patch(ProjectDTO entity) {
        String id = entity.getId();
        ProjectDTO projectDTO = this.get(id);
        modelMapper.map(entity, projectDTO);
        return update(projectDTO);
    }

    @Override
    public ProjectDTO update(ProjectDTO entity) {
        String id = entity.getId();
        if (id != null && repository.existsById(id)){
            Project project = modelMapper.map(entity, Project.class);
            project.setCreatedDate(repository.findById(id).get().getCreatedDate());
            project.setModifiedDate(new Date());
            repository.save(project);
            return entity;
        } else
            return null;
    }

    @Override
    public ProjectDTO get(String id) {
        Project project = repository.getOne(id);
        ProjectDTO projectDTO = modelMapper.map(project,ProjectDTO.class);
        return projectDTO;
    }

    @Override
    public List<ProjectDTO> getAll() {
        ProjectDTO projectDTO;
        List<ProjectDTO> all = new ArrayList<>();
        for(Project project: repository.findAll()){
            all.add(modelMapper.map(project, ProjectDTO.class));
        }
        return all;
    }

    @Override
    public ProjectDTO getCurrentProject() {
        Project project = userService.getCurrentUsername().getProject();
        return modelMapper.map(project, ProjectDTO.class);
    }
}

