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
import ru.nceduc.journal.service.AttendanceStudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/student/attendance")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description = "Operations pertaining to attendance of students in School Journal", tags = "ATTENDANCE-STUDENT-V1")
public class AttendanceStudentController {
    private final AttendanceStudentService attendanceStudentService;

    @ApiOperation(value = "Get all student attendances by group id")
    @GetMapping("/by-group/{groupId}")
    public ResponseEntity<List<AttendanceStudentDTO>> getAllByAttendanceByGroupId(@PathVariable String groupId) {
        return new ResponseEntity<>(attendanceStudentService.getAllByGroupId(groupId), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a student attendance")
    @DeleteMapping("/{id}")
    public ResponseEntity<AttendanceStudentDTO> deleteAttendanceStudent(@PathVariable String id) {
        attendanceStudentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Create a new student attendance")
    @PostMapping("/")
    public ResponseEntity<AttendanceStudentDTO> createAttendanceStudent(@RequestBody AttendanceStudentDTO attendanceStudentDTO) {
        Optional<AttendanceStudentDTO> optionalDTO = attendanceStudentService.create(attendanceStudentDTO);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation(value = "Get details of specific student attendance")
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceStudentDTO> getAttendanceStudent(@PathVariable String id) {
        Optional<AttendanceStudentDTO> optionalDTO = attendanceStudentService.get(id);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Get all student attendances by group id, month and year")
    @GetMapping("/by-filter/{filterId}")
    public ResponseEntity<List<AttendanceStudentDTO>> getAllByGroupDTOId(@PathVariable String filterId) {
        return new ResponseEntity<>(attendanceStudentService.getAllByGroupDTOId(filterId), HttpStatus.OK);
    }

}
