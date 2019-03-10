package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.ProjectDTO;
import ru.nceduc.journal.service.ProjectService;
import ru.nceduc.journal.service.impl.ProjectServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description="Operations pertaining to project in School Journal", tags = "PROJECT-V1")
public class ProjectController {

    private final ProjectService service;

    @ApiOperation(value = "Get all projects")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<ProjectDTO>> getAllProjects(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get current project ")
    @GetMapping("/current/")
    public ResponseEntity<List<ProjectDTO>> getProjectByUser(){
        return new ResponseEntity<>(service.getAllByUser(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get project details")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable String id){
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new project")
    @PostMapping("/")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        service.create(projectDTO);
        return new ResponseEntity<>(projectDTO, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update project")
    @PutMapping("/")
    public ResponseEntity<ProjectDTO> updateProject(@RequestBody ProjectDTO projectDTO) {
        service.update(projectDTO);
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Patch project")
    @PatchMapping("/")
    public ResponseEntity<ProjectDTO> patchProject(@RequestBody ProjectDTO projectDTO) {
        service.patch(projectDTO);
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete project")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteGroup(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
