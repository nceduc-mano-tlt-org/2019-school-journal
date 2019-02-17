package ru.nceduc.journal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.entity.Teacher;
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createTeacher(@RequestBody Teacher teacher, String groupId){
        teacherService.create(teacher, groupId);
    }

    @ApiOperation(value = "Update a teacher")
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTeacher(@PathVariable String id, @RequestBody Teacher teacher){
       teacherService.update(id, teacher);
    }

    @ApiOperation(value = "Patch a teacher")
    @PatchMapping("/patch")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchTeacher(@PathVariable String id){
        //TODO: Finish!!!
    }

    @ApiOperation(value = "Delete a teacher")
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeacher(@PathVariable String id){
        teacherService.delete(id);
    }
}
