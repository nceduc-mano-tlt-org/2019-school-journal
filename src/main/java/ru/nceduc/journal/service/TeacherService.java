package ru.nceduc.journal.service;

import ru.nceduc.journal.dto.TeacherDTO;

import java.util.List;

public interface TeacherService extends GenericService<TeacherDTO> {
    List<TeacherDTO> getAllByGroupId(String groupId);
}
