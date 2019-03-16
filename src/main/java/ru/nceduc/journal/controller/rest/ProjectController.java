package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.ProjectDTO;
import ru.nceduc.journal.service.ProjectService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description = "Operations pertaining to project in School Journal", tags = "PROJECT-V1")
public class ProjectController {

    private final ProjectService service;

    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Get all projects")
    @GetMapping("/")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get current projects")
    @GetMapping("/current/")
    public ResponseEntity<List<ProjectDTO>> getCurrentProjects() {
        return new ResponseEntity<>(service.getAllByCurrentUser(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get project details")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable String id) {
        Optional<ProjectDTO> optionalDTO = service.get(id);
        return optionalDTO.map(projectDTO -> new ResponseEntity<>(projectDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Create a new project")
    @PostMapping("/")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        Optional<ProjectDTO> optionalDTO = service.create(projectDTO);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation(value = "Update project")
    @PutMapping("/")
    public ResponseEntity<ProjectDTO> updateProject(@RequestBody ProjectDTO projectDTO) {
        Optional<ProjectDTO> optionalDTO = service.update(projectDTO);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation(value = "Patch project")
    @PatchMapping("/")
    public ResponseEntity<ProjectDTO> patchProject(@RequestBody ProjectDTO projectDTO) {
        Optional<ProjectDTO> optionalDTO = service.patch(projectDTO);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation(value = "Delete project")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteGroup(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
