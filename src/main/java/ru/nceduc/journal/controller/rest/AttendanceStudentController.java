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
import ru.nceduc.journal.dto.GroupDTO;
import ru.nceduc.journal.dto.StudentDTO;
import ru.nceduc.journal.service.AttendanceStudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/student/attendance")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description = "Operations pertaining to attendance of students in School Journal", tags = "ATTENDANCE-STUDENT-V1")
public class AttendanceStudentController {
    private final AttendanceStudentService attendanceStudentService;

    @ApiOperation(value = "Get all student by id")
    @GetMapping("/by-group/{studentId}")
    public ResponseEntity<List<AttendanceStudentDTO>> getAllByAttendanceStudentId(@PathVariable String studentId) {
        return new ResponseEntity<>(attendanceStudentService.getAllByGroupId(studentId), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a attendanceStudent")
    @DeleteMapping("/{id}")
    public ResponseEntity<AttendanceStudentDTO> deleteAttendanceStudent(@PathVariable String id) {
        attendanceStudentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Create a new attendanceStudent")
    @PostMapping("/")
    public ResponseEntity<AttendanceStudentDTO> createAttendanceStudent(@RequestBody AttendanceStudentDTO attendanceStudentDTO) {
        Optional<AttendanceStudentDTO> optionalDTO = attendanceStudentService.create(attendanceStudentDTO);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation(value = "Get details of specific AttendanceStudent")
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceStudentDTO> getAttendanceStudent(@PathVariable String id) {
        Optional<AttendanceStudentDTO> optionalDTO = attendanceStudentService.get(id);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Get all group by ID of attendance")
    @GetMapping("/by-group/{id}")
    public ResponseEntity<List<AttendanceGroupDTO>> getAllByGroupDTO(@PathVariable String id) {
        return new ResponseBody<>(attendanceStudentService.getAllByGroupDTO(), HttpStatus.OK);
    }

}
