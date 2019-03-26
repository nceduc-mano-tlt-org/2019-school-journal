package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.AttendanceDTO;
import ru.nceduc.journal.dto.StudentDTO;
import ru.nceduc.journal.entity.Attendance;
import ru.nceduc.journal.repository.AttendanceRepository;
import ru.nceduc.journal.repository.StudentRepository;
import ru.nceduc.journal.service.AttendanceService;

import java.util.*;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public Map<StudentDTO, List<Date>> getAttendanceByGroup(String groupId, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Date startDate = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = calendar.getTime();

        Map<StudentDTO, List<Date>> attendance = new HashMap<>();

        studentRepository.findAllByGroupId(groupId, Sort.by("lastName").ascending()).forEach((student) ->{
            List<Date> allDate = new ArrayList<>();
            attendanceRepository.findAllByGroup_IdAndStudent_IdAndDateVisitBetween(groupId,student.getId(),startDate,endDate).forEach(attendanceByStudent -> {
                allDate.add(attendanceByStudent.getDateVisit());
            });
            attendance.put(modelMapper.map(student,StudentDTO.class),allDate);
        });
        return attendance;
    }

    @Override
    public Optional<AttendanceDTO> create(AttendanceDTO attendanceDTO) {
        Optional<AttendanceDTO> optionalDTO = Optional.ofNullable(attendanceDTO);
        if (optionalDTO.isPresent()) {
            Attendance attendance = attendanceRepository.save(modelMapper.map(optionalDTO.get(), Attendance.class));
            return Optional.of(modelMapper.map(attendance, AttendanceDTO.class));
        } else
            return Optional.empty();
    }

    @Override
    public void delete(String groupId, String studentId) {
        if (attendanceRepository.existsByGroup_IdAndStudent_Id(groupId,studentId))
            attendanceRepository.deleteByGroup_IdAndStudent_Id(groupId,studentId);
    }
}
