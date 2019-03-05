package ru.nceduc.journal.service;

import ru.nceduc.journal.dto.SectionDTO;

import java.util.List;

public interface SectionService extends GenericService<SectionDTO> {
    List<SectionDTO> getAllByProjectId(String projectId);
}
