package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.AttendanceGroupDTO;
import ru.nceduc.journal.dto.GroupDTO;
import ru.nceduc.journal.entity.AttendanceGroup;
import ru.nceduc.journal.service.AttendanceGroupService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/group/attendance")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description = "Operations pertaining to attendance of groups in School Journal", tags = "ATTENDANCE-GROUP-V1")
public class AttendanceGroupController {
    private final AttendanceGroupService attendanceService;


    @ApiOperation(value = "Delete a attendance")
    @DeleteMapping("/{id}")
    public ResponseEntity<AttendanceGroupDTO> deleteAttendance(@PathVariable String id) {
        attendanceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Create a new attendance")
    @PostMapping("/")
    public ResponseEntity<AttendanceGroupDTO> createAttendance(@RequestBody AttendanceGroupDTO attendanceGroupDTO) {
        Optional<AttendanceGroupDTO> optionalDTO = attendanceService.create(attendanceGroupDTO);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation(value = "Get all attendance")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<AttendanceGroupDTO>> getAllAttendance() {
        return new ResponseEntity<>(attendanceService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get details of specific attendance")
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceGroupDTO> getAttendance(@PathVariable String id) {
        Optional<AttendanceGroupDTO> optionalDTO = attendanceService.get(id);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Get all groups by id")
    @GetMapping("/by-group/{groupId}")
    public ResponseEntity<List<AttendanceGroupDTO>> getAllByAttendanceId(@PathVariable String groupId) {
        return new ResponseEntity<>(attendanceService.getAllByGroupId(groupId), HttpStatus.OK);
    }



}
