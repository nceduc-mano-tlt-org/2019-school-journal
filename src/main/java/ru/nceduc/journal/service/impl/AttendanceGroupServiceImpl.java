package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.AttendanceGroupDTO;
import ru.nceduc.journal.entity.AttendanceGroup;
import ru.nceduc.journal.repository.AttendanceGroupRepository;
import ru.nceduc.journal.service.AttendanceGroupService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttendanceGroupServiceImpl implements AttendanceGroupService {

    private final AttendanceGroupRepository attendanceRepository;
    private final ModelMapper modelMapper;
    
    @Override
    public List<AttendanceGroupDTO> getAllByGroupId(String groupId) {
        List<AttendanceGroupDTO> attendanceGroupDTOS = new ArrayList<>();
        attendanceRepository.findAllByGroupId(groupId).forEach(attendance ->
                attendanceGroupDTOS.add(modelMapper.map(attendance, AttendanceGroupDTO.class)));
        return attendanceGroupDTOS;
    }

    @Override
    public Optional<AttendanceGroupDTO> get(String id) {
        return attendanceRepository.findById(id).map(attendance ->
                modelMapper.map(attendance, AttendanceGroupDTO.class));
    }

    @Override
    public List<AttendanceGroupDTO> getAll() {
        List<AttendanceGroupDTO> attendancesDTO = new ArrayList<>();
        attendanceRepository.findAll(Sort.by("createdDate").ascending()).forEach(attendance -> {
            attendancesDTO.add(modelMapper.map(attendance, AttendanceGroupDTO.class));
        });
        return attendancesDTO;
    }

    @Override
    public Optional<AttendanceGroupDTO> create(AttendanceGroupDTO entity) {
        Optional<AttendanceGroupDTO> optionalDTO = Optional.ofNullable(entity);
        if (optionalDTO.isPresent()) {
            AttendanceGroup attendance =
                    attendanceRepository.save(modelMapper.map(optionalDTO.get(), AttendanceGroup.class));
            return Optional.of(modelMapper.map(attendance, AttendanceGroupDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public void delete(String id) {
        if (id != null && attendanceRepository.existsById(id)){
            attendanceRepository.deleteById(id);
        }
    }
}
