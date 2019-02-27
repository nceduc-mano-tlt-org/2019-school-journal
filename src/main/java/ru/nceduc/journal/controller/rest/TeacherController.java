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

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value="teacher", description="Operations pertaining to teachers in School Journal")
public class TeacherController {
    private TeacherService teacherService;

    @ApiOperation(value = "Add a teacher")
    @PostMapping("/create")
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacherDTO, String groupId){
        teacherService.create(teacherDTO);
        return new ResponseEntity<>(teacherDTO, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a teacher")
    @PutMapping("/update")
    public ResponseEntity<TeacherDTO> updateTeacher(@RequestBody TeacherDTO teacherDTO){
        teacherService.update(teacherDTO);
        return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Patch a teacher")
    @PatchMapping("/patch")
    public ResponseEntity<TeacherDTO> patchTeacher(@RequestBody TeacherDTO teacherDTO){
        teacherService.patch(teacherDTO);
        return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a teacher")
    @DeleteMapping("/delete")
    public ResponseEntity deleteTeacher(@PathVariable String id){
        teacherService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
