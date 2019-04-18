package ru.nceduc.journal.service;

import ru.nceduc.journal.dto.AttendanceStudentDTO;

import java.util.List;
import java.util.Optional;

public interface AttendanceStudentService{
    List<AttendanceStudentDTO> getAllByFilterId(String id);

    List<AttendanceStudentDTO> getAllByGroupId(String id);

    List<AttendanceStudentDTO> getAll();

    Optional<AttendanceStudentDTO> get(String id);

    Optional<AttendanceStudentDTO> create(AttendanceStudentDTO attendanceStudentDTO);

    void delete(String id);
}
