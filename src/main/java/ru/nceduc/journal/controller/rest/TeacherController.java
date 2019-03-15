package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.TeacherDTO;
import ru.nceduc.journal.service.TeacherService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description="Operations pertaining to teachers in School Journal", tags = "TEACHER-V1")
public class TeacherController {

    private final TeacherService teacherService;

    @ApiOperation(value = "Get all teachers")
    @GetMapping("/")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers(){
        return new ResponseEntity<>(teacherService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get details of specific teacher")
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacher(@PathVariable String id){
        Optional<TeacherDTO> optionalDTO = teacherService.get(id);
        return optionalDTO.map(teacherDTO -> new ResponseEntity<>(teacherDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Get all teachers by ID of group")
    @GetMapping("/by-group/{id}")
    public ResponseEntity<List<TeacherDTO>> getAllTeachersByGroupId(@PathVariable String id){
        return new ResponseEntity<>(teacherService.getAllByGroupId(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new teacher")
    @PostMapping("/")
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacherDTO){
        Optional<TeacherDTO> optionalDTO = teacherService.create(teacherDTO);
        return optionalDTO.map(teacherDTO1 -> new ResponseEntity<>(teacherDTO1, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Update teacher's details")
    @PutMapping("/")
    public ResponseEntity<TeacherDTO> updateTeacher(@RequestBody TeacherDTO teacherDTO){
        Optional<TeacherDTO> optionalDTO = teacherService.update(teacherDTO);
        return optionalDTO.map(teacherDTO1 -> new ResponseEntity<>(teacherDTO1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Patch teacher's details")
    @PatchMapping("/")
    public ResponseEntity<TeacherDTO> patchTeacher(@RequestBody TeacherDTO teacherDTO){
        Optional<TeacherDTO> optionalDTO = teacherService.patch(teacherDTO);
        return optionalDTO.map(teacherDTO1 -> new ResponseEntity<>(teacherDTO1 , HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Delete a teacher")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTeacher(@PathVariable String id){
        teacherService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
