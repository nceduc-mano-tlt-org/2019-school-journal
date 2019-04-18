package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.AttendanceFilterDTO;
import ru.nceduc.journal.service.AttendanceFilterService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/attendance/filter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description = "Operations pertaining to filters of attendance in School Journal", tags = "ATTENDANCE-FILTER-V1")
public class AttendanceFilterController {
    private final AttendanceFilterService attendanceService;


    @ApiOperation(value = "Delete a filter")
    @DeleteMapping("/{id}")
    public ResponseEntity<AttendanceFilterDTO> deleteFilter(@PathVariable String id) {
        attendanceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Create a new filter")
    @PostMapping("/")
    public ResponseEntity<AttendanceFilterDTO> createFilter(@RequestBody AttendanceFilterDTO attendanceFilterDTO) {
        Optional<AttendanceFilterDTO> optionalDTO = attendanceService.create(attendanceFilterDTO);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation(value = "Get all filters")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<AttendanceFilterDTO>> getAllFilters() {
        return new ResponseEntity<>(attendanceService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get details of specific filter")
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceFilterDTO> getFilter(@PathVariable String id) {
        Optional<AttendanceFilterDTO> optionalDTO = attendanceService.get(id);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Get all filters by group id")
    @GetMapping("/by-filter/{groupId}")
    public ResponseEntity<List<AttendanceFilterDTO>> getAllAttendancesByGroupId(@PathVariable String groupId) {
        return new ResponseEntity<>(attendanceService.getAllByGroupId(groupId), HttpStatus.OK);
    }



}
