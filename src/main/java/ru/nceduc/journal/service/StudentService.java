package ru.nceduc.journal.service;

import ru.nceduc.journal.dto.StudentDTO;

import java.util.List;

public interface StudentService extends GenericService<StudentDTO> {
    List<StudentDTO> getAllByGroupId(String groupId);
}
