package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.AttendanceGroupDTO;
import ru.nceduc.journal.dto.AttendanceStudentDTO;
import ru.nceduc.journal.dto.StudentDTO;
import ru.nceduc.journal.service.AttendanceGroupService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/group/attendance")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description = "Operations pertaining to attendance in School Journal", tags = "ATTENDANCE-V1")
public class AttendanceController {

    private final AttendanceGroupService attendanceService;

    @ApiOperation(value = "Get all attendances by group")
    @GetMapping("/")
    public ResponseEntity<Map<StudentDTO, List<Date>>> getAttendanceByGroup(@RequestBody AttendanceGroupDTO attendanceGroupDTO) {
        return new ResponseEntity<>(attendanceService.getAttendanceByGroup(attendanceGroupDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new attendance")
    @PostMapping("/")
    public ResponseEntity<AttendanceStudentDTO> createAttendance(@RequestBody AttendanceStudentDTO attendanceStudentDTO) {
        Optional<AttendanceStudentDTO> optionalDTO = attendanceService.create(attendanceStudentDTO);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation(value = "Delete project")
    @DeleteMapping("/")
    public ResponseEntity deleteAttendance(@RequestBody AttendanceStudentDTO attendanceStudentDTO) {
        attendanceService.delete(attendanceStudentDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
