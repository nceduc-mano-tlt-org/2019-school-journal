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
import ru.nceduc.journal.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public Optional<ProjectDTO> create(ProjectDTO entity) {
        Optional<ProjectDTO> optionalDTO = Optional.ofNullable(entity);
        if (optionalDTO.isPresent()) {
            Project project = repository.save(modelMapper.map(optionalDTO.get(), Project.class));
            return Optional.of(modelMapper.map(project, ProjectDTO.class));
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
        Optional<ProjectDTO> patchedOptional = Optional.ofNullable(entity);
        if (patchedOptional.isPresent()) {
            Optional<ProjectDTO> currentOptional = get(entity.getId());

            if (currentOptional.isPresent()) {
                ProjectDTO patchedDTO = patchedOptional.get();
                ProjectDTO currentDTO = currentOptional.get();
                modelMapper.map(patchedDTO, currentDTO);
                return save(currentDTO);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<ProjectDTO> update(ProjectDTO entity) {
        Optional<ProjectDTO> optionalDTO = Optional.ofNullable(entity);
        if (optionalDTO.isPresent()) {
            return save(optionalDTO.get());
        }
        return Optional.empty();
    }

    @Override
    public Optional<ProjectDTO> get(String id) {
        return repository.findById(id).map(project -> modelMapper.map(project, ProjectDTO.class));
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
    public List<ProjectDTO> getAllByCurrentUser() {
        UserEntity user = userService.getCurrentUser();
        List<ProjectDTO> projectDTO = new ArrayList<>();
        repository.findAllByUser(user, Sort.by("createdDate").ascending()).forEach(project -> {
            projectDTO.add(modelMapper.map(project, ProjectDTO.class));
        });
        return projectDTO;
    }

    private Optional<ProjectDTO> save(ProjectDTO projectDTO) {
        Project project = modelMapper.map(projectDTO, Project.class);

        return repository.findById(project.getId()).map(entity -> {
            project.setModifiedDate(new Date());
            project.setCreatedDate(entity.getCreatedDate());
            project.setUser(entity.getUser());
            Project savedProject = repository.save(project);
            return modelMapper.map(savedProject, ProjectDTO.class);
        });
    }
}