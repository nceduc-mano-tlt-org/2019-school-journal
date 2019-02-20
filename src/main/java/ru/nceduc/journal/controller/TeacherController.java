package ru.nceduc.journal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.TeacherDTO;
import ru.nceduc.journal.service.TeacherService;

@Controller
@RequestMapping("/teacher")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value="teacher", description="Operations pertaining to teachers in School Journal")
public class TeacherController {
    private TeacherService teacherService;

    /*
    @ApiOperation(value = "View a list of existing teachers", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )

    @ApiOperation(value = "Search a teacher with an ID", response = Teacher.class)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String getTeacher(@PathVariable String id){
        return teacherService.get(id).toString();
    }
*/

    @ApiOperation(value = "Add a teacher")
    @PostMapping("/create")
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacherDTO, String groupId){
        teacherService.create(teacherDTO, groupId);
        return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
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
