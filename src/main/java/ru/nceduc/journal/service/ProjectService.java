package ru.nceduc.journal.service;

import ru.nceduc.journal.dto.ProjectDTO;

import java.util.List;

public interface ProjectService extends GenericService<ProjectDTO> {
    ProjectDTO getCurrentProject();
    List<ProjectDTO> getAllByUser();
}
