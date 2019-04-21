package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.AttendanceFilterDTO;
import ru.nceduc.journal.entity.AttendanceFilter;
import ru.nceduc.journal.repository.AttendanceFilterRepository;
import ru.nceduc.journal.service.AttendanceFilterService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttendanceFilterServiceImpl implements AttendanceFilterService {

    private final AttendanceFilterRepository attendanceRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AttendanceFilterDTO> getAllByGroupId(String groupId) {
        List<AttendanceFilterDTO> attendanceFilterDTOS = new ArrayList<>();
        attendanceRepository.findAllByGroupId(groupId).forEach(attendance ->
                attendanceFilterDTOS.add(modelMapper.map(attendance, AttendanceFilterDTO.class)));
        return attendanceFilterDTOS;
    }

    @Override
    public Optional<AttendanceFilterDTO> get(String id) {
        return attendanceRepository.findById(id).map(attendance ->
                modelMapper.map(attendance, AttendanceFilterDTO.class));
    }

    @Override
    public List<AttendanceFilterDTO> getAll() {
        List<AttendanceFilterDTO> attendancesDTO = new ArrayList<>();
        attendanceRepository.findAll(Sort.by("createdDate").ascending()).forEach(attendance -> {
            attendancesDTO.add(modelMapper.map(attendance, AttendanceFilterDTO.class));
        });
        return attendancesDTO;
    }

    @Override
    public Optional<AttendanceFilterDTO> create(AttendanceFilterDTO entity) {
        Optional<AttendanceFilterDTO> optionalDTO = Optional.ofNullable(entity);
        if (optionalDTO.isPresent()) {
            AttendanceFilter attendance =
                    attendanceRepository.save(modelMapper.map(optionalDTO.get(), AttendanceFilter.class));
            return Optional.of(modelMapper.map(attendance, AttendanceFilterDTO.class));
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
