package ru.nceduc.journal.service;

import ru.nceduc.journal.dto.AttendanceFilterDTO;

import java.util.List;
import java.util.Optional;

public interface AttendanceFilterService {
    List<AttendanceFilterDTO> getAllByGroupId(String groupId);

    List<AttendanceFilterDTO> getAll();

    Optional<AttendanceFilterDTO> get(String id);

    Optional<AttendanceFilterDTO> create(AttendanceFilterDTO attendanceFilterDTO);

    void delete(String id);
}
