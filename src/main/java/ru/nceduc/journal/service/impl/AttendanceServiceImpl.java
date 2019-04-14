package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.AttendanceGroupDTO;
import ru.nceduc.journal.dto.AttendanceStudentDTO;
import ru.nceduc.journal.dto.StudentDTO;
import ru.nceduc.journal.entity.AttendanceGroup;
import ru.nceduc.journal.repository.AttendanceGroupRepository;
import ru.nceduc.journal.repository.StudentRepository;
import ru.nceduc.journal.service.AttendanceGroupService;

import java.util.*;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttendanceServiceImpl implements AttendanceGroupService {

    private final AttendanceGroupRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public Map<StudentDTO, List<Date>> getAttendanceByGroup(AttendanceGroupDTO attendanceGroupDTO) {
        return null;
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
        return attendanceRepository.findById(id).map(attendance -> modelMapper.map(attendance, AttendanceStudentDTO.class));
    }

    @Override
    public Optional<AttendanceStudentDTO> create(AttendanceStudentDTO attendanceStudentDTO) {
        Optional<AttendanceStudentDTO> optionalDTO = Optional.ofNullable(attendanceStudentDTO);
        if (optionalDTO.isPresent()) {
            AttendanceGroup attendance = attendanceRepository.save(modelMapper.map(optionalDTO.get(), AttendanceGroup.class));

        }
        return Optional.empty();
    }

    @Override
    public void delete(AttendanceStudentDTO attendanceStudentDTO) {

    }

//    @Override
//    public Map<StudentDTO, List<Date>> getAttendanceByGroup(AttendanceGroupDTO attendanceGroupDTO) {
//        String groupId = attendanceGroupDTO.getGroupId();
//        Date dateAttendance = attendanceGroupDTO.getDateVisit();
//
//        Calendar calendar = Calendar.getInstance();
//        if (dateAttendance != null) {
//            calendar.setTime(dateAttendance);
//        }
//        calendar.set(Calendar.DAY_OF_MONTH,1);
//        Date startDate = calendar.getTime();
//        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//        Date endDate = calendar.getTime();
//
//        Map<StudentDTO, List<Date>> attendance = new HashMap<>();
//
//        studentRepository.findAllByGroupId(groupId, Sort.by("lastName").ascending()).forEach((student) ->{
//            List<Date> allDate = new ArrayList<>();
//            attendanceRepository.findAllByGroup_IdAndStudent_IdAndDateVisitBetween(groupId,student.getId(),startDate,endDate).forEach(attendanceByStudent -> {
//                allDate.add(attendanceByStudent.getDateVisit());
//            });
//            attendance.put(modelMapper.map(student,StudentDTO.class),allDate);
//        });
//        return attendance;
//    }
//
//    @Override
//    public Optional<AttendanceStudentDTO> create(AttendanceStudentDTO attendanceStudentDTO) {
//        Optional<AttendanceStudentDTO> optionalDTO = Optional.ofNullable(attendanceStudentDTO);
//        if (optionalDTO.isPresent()) {
//            optionalDTO.get().setDateVisit(new Date());
//            AttendanceGroup attendance = attendanceRepository.save(modelMapper.map(optionalDTO.get(), AttendanceGroup.class));
//            return Optional.of(modelMapper.map(attendance, AttendanceStudentDTO.class));
//        } else
//            return Optional.empty();
//    }
//
//    @Override
//    public void delete(AttendanceStudentDTO attendanceStudentDTO) {
//        String groupId = attendanceStudentDTO.getGroupId();
//        String studentId = attendanceStudentDTO.getStudentId();
//        Date date = attendanceStudentDTO.getDateVisit();
//        if (attendanceRepository.existsByGroup_IdAndStudent_IdAndDateVisit(groupId, studentId, date))
//            attendanceRepository.deleteByGroup_IdAndStudent_IdAndDateVisit(groupId, studentId, date);
//    }
}
