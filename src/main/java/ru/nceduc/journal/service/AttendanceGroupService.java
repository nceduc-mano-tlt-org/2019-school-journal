package ru.nceduc.journal.service;

import ru.nceduc.journal.dto.AttendanceGroupDTO;
import ru.nceduc.journal.dto.GroupDTO;

import java.util.List;
import java.util.Optional;

public interface AttendanceGroupService{
    List<AttendanceGroupDTO> getAllByGroupId(String groupId);

    List<AttendanceGroupDTO> getAll();

    Optional<AttendanceGroupDTO> get(String id);

    Optional<AttendanceGroupDTO> create(AttendanceGroupDTO attendanceGroupDTO);

    void delete(String id);
}
