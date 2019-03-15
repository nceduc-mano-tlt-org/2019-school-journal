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
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description="Operations pertaining to students in School Journal", tags = "STUDENT-V1")
public class StudentController {

    private final StudentService studentService;

    @ApiOperation(value = "Get all students")
    @GetMapping("/")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get specific student details")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable String id) {
        Optional<StudentDTO> optionalDTO = studentService.get(id);
        return optionalDTO.map(studentDTO -> new ResponseEntity<>(studentDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Get all students by ID of group")
    @GetMapping("/by-group/{id}")
    public ResponseEntity<List<StudentDTO>> getAllStudentsByGroupId(@PathVariable String id) {
        return new ResponseEntity<>(studentService.getAllByGroupId(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new student")
    @PostMapping("/")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        Optional<StudentDTO> optionalDTO = studentService.create(studentDTO);
        return optionalDTO.map(studentDTO1 -> new ResponseEntity<>(studentDTO1, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Update student details")
    @PutMapping("/")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) {
        Optional<StudentDTO> optionalDTO = studentService.update(studentDTO);
        return optionalDTO.map(studentDTO1 -> new ResponseEntity<>(studentDTO1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Patch student details")
    @PatchMapping("/")
    public ResponseEntity<StudentDTO> patchStudent(@RequestBody StudentDTO studentDTO) {
        Optional<StudentDTO> optionalDTO = studentService.patch(studentDTO);
        return optionalDTO.map(studentDTO1 -> new ResponseEntity<>(studentDTO1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Delete a student")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

