package ru.nceduc.journal.service;

import ru.nceduc.journal.dto.AttendanceDTO;
import ru.nceduc.journal.dto.StudentDTO;
import ru.nceduc.journal.entity.Student;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AttendanceService {
    Map<StudentDTO, List<Date>> getAttendanceByGroup(String groupId, Date date);
    Optional<AttendanceDTO> create(AttendanceDTO attendanceDTO);
    void delete(String groupId, String studentId);
}
