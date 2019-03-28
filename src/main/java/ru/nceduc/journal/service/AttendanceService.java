package ru.nceduc.journal.service;

import ru.nceduc.journal.dto.AttendanceGroupDTO;
import ru.nceduc.journal.dto.AttendanceStudentDTO;
import ru.nceduc.journal.dto.StudentDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AttendanceService {
    Map<StudentDTO, List<Date>> getAttendanceByGroup(AttendanceGroupDTO attendanceGroupDTO);

    Optional<AttendanceStudentDTO> create(AttendanceStudentDTO attendanceStudentDTO);

    void delete(AttendanceStudentDTO attendanceStudentDTO);
}
