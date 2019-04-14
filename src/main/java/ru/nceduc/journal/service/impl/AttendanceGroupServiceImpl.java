package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.AttendanceGroupDTO;
import ru.nceduc.journal.repository.AttendanceGroupRepository;
import ru.nceduc.journal.service.AttendanceGroupService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttendanceGroupServiceImpl implements AttendanceGroupService {

    private final AttendanceGroupRepository attendanceRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AttendanceGroupDTO> getAllByGroupDTO(AttendanceGroupDTO attendanceGroupDTO) {
        return null;
    }

    @Override
    public List<AttendanceGroupDTO> getAllByGroupId(String groupId) {
        return null;
    }

    @Override
    public Optional<AttendanceGroupDTO> get(String id) {
        return Optional.empty();
    }

    @Override
    public List<AttendanceGroupDTO> getAll() {
        return null;
    }

    @Override
    public Optional<AttendanceGroupDTO> create(AttendanceGroupDTO entity) {
        return Optional.empty();
    }

    @Override
    public void delete(String id) {

    }
}
