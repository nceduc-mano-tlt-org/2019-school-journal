package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.AttendanceGroupDTO;
import ru.nceduc.journal.dto.AttendanceStudentDTO;
import ru.nceduc.journal.entity.AttendanceStudent;
import ru.nceduc.journal.repository.AttendanceStudentRepository;
import ru.nceduc.journal.service.AttendanceGroupService;
import ru.nceduc.journal.service.AttendanceStudentService;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttendanceStudentServiceImpl implements AttendanceStudentService {

    private final AttendanceGroupService attendanceGroupService;
    private final AttendanceStudentRepository attendanceRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AttendanceStudentDTO> getAllByGroupDTO(AttendanceGroupDTO attendanceGroupDTO) {
        String groupId = attendanceGroupDTO.getGroupId();

        Month month = Month.of(attendanceGroupDTO.getMonth());
        List<AttendanceStudentDTO> attendanceStudentDTOS = new ArrayList<>();
        attendanceRepository.findAllByGroupId(groupId).forEach(attendance ->
                attendanceStudentDTOS.add(modelMapper.map(attendance, AttendanceStudentDTO.class)));

    }

    @Override
    public List<AttendanceStudentDTO> getAllByGroupId(String id) {
        List<AttendanceStudentDTO> attendanceStudentDTOS = new ArrayList<>();
        attendanceRepository.findAllByGroupId(id).forEach(attendance ->
                attendanceStudentDTOS.add(modelMapper.map(attendance, AttendanceStudentDTO.class)));
        return attendanceStudentDTOS;
    }

    @Override
    public List<AttendanceStudentDTO> getAll() {
        List<AttendanceStudentDTO> attendancesDTO = new ArrayList<>();
        attendanceRepository.findAll(Sort.by("createdDate").ascending()).forEach(attendance -> {
            attendancesDTO.add(modelMapper.map(attendance, AttendanceStudentDTO.class));
        });
        return attendancesDTO;
    }

    @Override
    public Optional<AttendanceStudentDTO> get(String id) {
        return attendanceRepository.findById(id).map(attendance ->
                modelMapper.map(attendance, AttendanceStudentDTO.class));
    }

    @Override
    public Optional<AttendanceStudentDTO> create(AttendanceStudentDTO attendanceStudentDTO) {
        Optional<AttendanceStudentDTO> optionalDTO = Optional.ofNullable(attendanceStudentDTO);
        if (optionalDTO.isPresent()) {
            AttendanceStudent attendance =
                    attendanceRepository.save(modelMapper.map(optionalDTO.get(), AttendanceStudent.class));
            return Optional.of(modelMapper.map(attendance, AttendanceStudentDTO.class));
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
