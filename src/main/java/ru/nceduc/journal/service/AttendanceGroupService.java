package ru.nceduc.journal.service;

import ru.nceduc.journal.dto.AttendanceGroupDTO;

import java.util.List;
import java.util.Optional;

public interface AttendanceGroupService extends GenericService<AttendanceGroupDTO> {
    List<AttendanceGroupDTO> getAllByGroupDTO(AttendanceGroupDTO attendanceGroupDTO);
    List<AttendanceGroupDTO> getAllByGroupId(String groupId);
}
