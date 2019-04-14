package ru.nceduc.journal.service;

import ru.nceduc.journal.dto.AttendanceStudentDTO;

import java.util.List;
import java.util.Optional;

public interface AttendanceStudentService extends GenericService<AttendanceStudentDTO>{
    List<AttendanceStudentDTO> getAllByGroupId(String id);
}
