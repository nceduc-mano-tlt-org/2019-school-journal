package ru.nceduc.journal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.StudentDTO;
import ru.nceduc.journal.service.StudentService;

import java.util.Map;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value="journal", description="Operations pertaining to elemnts in School Journal")
public class StudentController {
    private StudentService studentService;

    /*
    @ApiOperation(value = "View a list of existing teachers", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )

    @ApiOperation(value = "View a list of existing students", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping("/student/all")
    public List<String> getAllStudents(){
        List<StudentDTO> studentDTOS = studentService.getAll();
        List<String> result = new ArrayList<>();
        for (StudentDTO element : studentDTOS){
            result.add(element.toString());
        }
        return result;
    }

    @ApiOperation(value = "Search a Student with an ID", response = Student.class)
    public String getStudent(@PathVariable String id){
        return studentService.get(id).toString();
    }
*/

    @ApiOperation(value = "Add a student")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO, String groupId){
        studentService.create(studentDTO, groupId);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Update a student")
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO){
        studentService.update(studentDTO);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Patch a student")
    @PatchMapping("/patch")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<StudentDTO> patchStudent(@RequestBody StudentDTO studentDTO){
        studentService.patch(studentDTO);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a student")
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteStudent(@PathVariable String id){
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
