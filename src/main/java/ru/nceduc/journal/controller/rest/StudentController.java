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

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value="journal", description="Operations pertaining to elemnts in School Journal")
public class StudentController {
    private StudentService studentService;

    @ApiOperation(value = "Add a student")
    @PostMapping("/create")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO, String groupId){
        studentService.create(studentDTO);
        return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a student")
    @PutMapping("/update")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO){
        studentService.update(studentDTO);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Patch a student")
    @PatchMapping("/patch")
    public ResponseEntity<StudentDTO> patchStudent(@RequestBody StudentDTO studentDTO){
        studentService.patch(studentDTO);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a student")
    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@PathVariable String id){
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
