package ru.nceduc.journal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.StudentDTO;
import ru.nceduc.journal.dto.TeacherDTO;
import ru.nceduc.journal.entity.Student;
import ru.nceduc.journal.entity.Teacher;
import ru.nceduc.journal.service.StudentService;
import ru.nceduc.journal.service.TeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Api(value="journal", description="Operations pertaining to elemnts in School Journal")
public class JournalController {
    private TeacherService teacherService;
    private StudentService studentService;

    @Autowired
    public JournalController(TeacherService teacherService, StudentService studentService){
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @ApiOperation(value = "View a list of existing teachers", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping("/teacher/all")
    public List<String> getAllTeachers(){
        List<TeacherDTO> teacherDTOS = teacherService.getAll();
        List<String> result = new ArrayList<>();
        for(TeacherDTO element : teacherDTOS){
            result.add(element.toString());
        }
        return result;
    }

    @ApiOperation(value = "Search a teacher with an ID", response = Teacher.class)
    @GetMapping("/teacher/{id}")
    public String getTeacher(@PathVariable String id){
        return teacherService.get(id).toString();
    }

    @ApiOperation(value = "Add a teacher")
    @PostMapping("/teacher")
    public ResponseEntity createTeacher(@RequestBody Teacher teacher){
        teacherService.create(teacher);
        return ResponseEntity.ok("Teacher " + teacher.getFirstName() + " " + teacher.getLastName() + " created!");
    }

    @ApiOperation(value = "Update a teacher")
    @PutMapping("/teacher/{id}")
    public ResponseEntity updateTeacher(@PathVariable String id, @RequestBody Teacher teacher){
       teacherService.update(id, teacher);
        return ResponseEntity.ok("Teacher " + id + " updated!");
    }

    @ApiOperation(value = "Patch a teacher")
    @PatchMapping("/teacher/{id}")
    public ResponseEntity patchTeacher(@PathVariable String id, Map<String, Teacher> patchValues){
        //TODO: Доделать!!!
        return  ResponseEntity.ok("Teacher " + id + " patched!");
    }

    @ApiOperation(value = "Delete a teacher")
    @DeleteMapping("/teacher/{id}")
    public ResponseEntity deleteTeacher(@PathVariable String id){
        teacherService.delete(id);
        return  ResponseEntity.ok("Teacher " + id + " deleted!");
    }

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
    @GetMapping("/student/{id}")
    public String getStudent(@PathVariable String id){
        return studentService.get(id).toString();
    }

    @ApiOperation(value = "Add a student")
    @PostMapping("/student")
    public ResponseEntity createStudent(@RequestBody Student student){
        studentService.create(student);
        return ResponseEntity.ok("Student " + student.getFirstName() + " " + student.getLastName() + " created!");
    }

    @ApiOperation(value = "Update a student")
    @PutMapping("/student/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @RequestBody Student student){
        studentService.update(id, student);
        return  ResponseEntity.ok("Student " + id + "updated!");
    }

    @ApiOperation(value = "Patch a student")
    @PatchMapping("/student/{id}")
    public ResponseEntity patchStudent(@PathVariable String id, Map<String, Teacher> patchValues){
        //TODO: Доделать!!!
        return  ResponseEntity.ok("Student " + id + " patched!");
    }

    @ApiOperation(value = "Delete a student")
    @DeleteMapping("/student/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id){
        studentService.delete(id);
        return  ResponseEntity.ok("Student " + id + " deleted!");
    }
}
