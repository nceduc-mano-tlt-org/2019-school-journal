package ru.nceduc.journal.controller;

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
@RequestMapping(value = "/api/v1")
public class JournalController {
    private TeacherService teacherService;
    private StudentService studentService;

    @Autowired
    public JournalController(TeacherService teacherService, StudentService studentService){
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @GetMapping("/teacher/all")
    public List<String> getAllTeachers(){
        List<TeacherDTO> teacherDTOS = teacherService.getAll();
        List<String> result = new ArrayList<>();
        for(TeacherDTO element : teacherDTOS){
            result.add(element.toString());
        }
        return result;
    }

    @GetMapping("/teacher/{id}")
    public String getTeacher(@PathVariable String id){
        return teacherService.get(id).toString();
    }

    @PostMapping("/teacher")
    public ResponseEntity createTeacher(@RequestBody Teacher teacher){
        teacherService.create(teacher);
        return ResponseEntity.ok("Teacher " + teacher.getFirstName() + " " + teacher.getLastName() + " created!");
    }

    @PutMapping("/teacher/{id}")
    public ResponseEntity updateTeacher(@PathVariable String id, @RequestBody Teacher teacher){
       teacherService.update(id, teacher);
        return ResponseEntity.ok("Teacher " + id + " updated!");
    }

    @PatchMapping("/teacher/{id}")
    public ResponseEntity patchTeacher(@PathVariable String id, Map<String, Teacher> patchValues){
        //TODO: Доделать!!!
        return  ResponseEntity.ok("Teacher " + id + " patched!");
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity deleteTeacher(@PathVariable String id){
        teacherService.delete(id);
        return  ResponseEntity.ok("Teacher " + id + " deleted!");
    }

    @GetMapping("/student/all")
    public List<String> getAllStudents(){
        List<StudentDTO> studentDTOS = studentService.getAll();
        List<String> result = new ArrayList<>();
        for (StudentDTO element : studentDTOS){
            result.add(element.toString());
        }
        return result;
    }

    @GetMapping("/student/{id}")
    public String getStudent(@PathVariable String id){
        return studentService.get(id).toString();
    }

    @PostMapping("/student")
    public ResponseEntity createStudent(@RequestBody Student student){
        studentService.create(student);
        return ResponseEntity.ok("Student " + student.getFirstName() + " " + student.getLastName() + " created!");
    }

    @PutMapping("/student/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @RequestBody Student student){
        studentService.update(id, student);
        return  ResponseEntity.ok("Student " + id + "updated!");
    }

    @PatchMapping("/student/{id}")
    public ResponseEntity patchStudent(@PathVariable String id, Map<String, Teacher> patchValues){
        //TODO: Доделать!!!
        return  ResponseEntity.ok("Student " + id + " patched!");
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id){
        studentService.delete(id);
        return  ResponseEntity.ok("Student " + id + " deleted!");
    }
}
