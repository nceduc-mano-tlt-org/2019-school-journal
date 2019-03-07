package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.ProjectDTO;
import ru.nceduc.journal.service.impl.ProjectServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectController {

    private final ProjectServiceImpl service;

    @ApiOperation(value = "Create a new project")
    @PostMapping("/")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        service.create(projectDTO);
        return new ResponseEntity<>(projectDTO, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Patch project")
    @PatchMapping("/")
    public ResponseEntity<ProjectDTO> patchProject(@RequestBody ProjectDTO projectDTO) {
        service.patch(projectDTO);
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Update project")
    @PutMapping("/")
    public ResponseEntity<ProjectDTO> updateGroup(@RequestBody ProjectDTO projectDTO) {
        service.update(projectDTO);
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete project")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteGroup(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Get project details")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable String id){
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all projects")
    @GetMapping("/")
    public ResponseEntity<List<ProjectDTO>> getAllProjects(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}
