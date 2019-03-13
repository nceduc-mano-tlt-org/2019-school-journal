
package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.ProjectDTO;
import ru.nceduc.journal.entity.Project;
import ru.nceduc.journal.entity.UserEntity;
import ru.nceduc.journal.repository.ProjectRepository;
import ru.nceduc.journal.service.ProjectService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;

    @Override
    public Optional<ProjectDTO> create(ProjectDTO entity) {
        Optional<ProjectDTO> optionalDTO = Optional.ofNullable(entity);
        if (optionalDTO.isPresent()) {
            Project project = repository.save(modelMapper.map(optionalDTO.get(), Project.class));
            return Optional.of(modelMapper.map(project,ProjectDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public void delete(String id) {
        if (id != null && repository.findById(id).isPresent())
            repository.deleteById(id);
    }

    @Override
    public Optional<ProjectDTO> patch(ProjectDTO entity) {
        Optional<ProjectDTO> optionalDTO = Optional.ofNullable(entity);
        if (optionalDTO.isPresent() && repository.findById(optionalDTO.get().getId()).isPresent()) {
            ProjectDTO projectDTO = modelMapper.map(this.get(optionalDTO.get().getId()), ProjectDTO.class);
            modelMapper.map(optionalDTO.get(), projectDTO);
            return update(projectDTO);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ProjectDTO> update(ProjectDTO entity) {
        Optional<ProjectDTO> optionalDTO = Optional.of(entity);
        String id = optionalDTO.get().getId();
        if (id != null && repository.findById(id).isPresent()) {
            Project project = modelMapper.map(optionalDTO.get(), Project.class);
            project.setCreatedDate(repository.findById(id).get().getCreatedDate());
            project.setModifiedDate(new Date());
            project = repository.save(project);
            return Optional.of(modelMapper.map(project,ProjectDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ProjectDTO> get(String id) {
        if(repository.findById(id).isPresent()){
            return Optional.of(modelMapper.map(repository.findById(id),ProjectDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public List<ProjectDTO> getAll() {
        List<ProjectDTO> projectDTO = new ArrayList<>();
        repository.findAll(Sort.by("createdDate").ascending()).forEach(project -> {
            projectDTO.add(modelMapper.map(project, ProjectDTO.class));
        });
        return projectDTO;
    }

    @Override
    public ProjectDTO getCurrentProject() {
        Project project = userService.getCurrentUser().getProject();
        return modelMapper.map(project, ProjectDTO.class);
    }

    @Override
    public List<ProjectDTO> getAllByUser() {
        UserEntity user = userService.getCurrentUser();
        List<ProjectDTO> projectDTO = new ArrayList<>();
        repository.findAllByUser(user, Sort.by("createdDate").ascending()).forEach(project -> {
            projectDTO.add(modelMapper.map(project, ProjectDTO.class));
        });
        return projectDTO;
    }
}