package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.StudentDTO;
import ru.nceduc.journal.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description="Operations pertaining to students in School Journal", tags = "STUDENT-V1")
public class StudentController {
    private StudentService studentService;

    @ApiOperation(value = "Create a new student")
    @PostMapping("/")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        studentService.create(studentDTO);
        return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update student details")
    @PutMapping("/")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) {
        studentService.update(studentDTO);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Patch student details")
    @PatchMapping("/")
    public ResponseEntity<StudentDTO> patchStudent(@RequestBody StudentDTO studentDTO) {
        studentService.patch(studentDTO);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a student")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Get specific student details")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable String id) {
        return new ResponseEntity<>(studentService.get(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all students")
    @GetMapping("/")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all students by ID of group")
    @GetMapping("/by-group/{id}")
    public ResponseEntity<List<StudentDTO>> getAllStudentsByGroupId(@PathVariable String id) {
        return new ResponseEntity<>(studentService.getAllByGroupId(id), HttpStatus.OK);
    }
}

