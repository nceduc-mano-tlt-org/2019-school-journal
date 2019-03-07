package ru.nceduc.journal.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.ProjectDTO;
import ru.nceduc.journal.service.impl.ProjectServiceImpl;

@Controller
@RequestMapping("project/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectController {

    private final ProjectServiceImpl service;

    @PostMapping("create")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        service.create(projectDTO);
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @PatchMapping("patch")
    public ResponseEntity<ProjectDTO> patchProject(@RequestBody ProjectDTO projectDTO) {
        service.patch(projectDTO);
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<ProjectDTO> updateGroup(@RequestBody ProjectDTO projectDTO) {
        service.update(projectDTO);
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity deleteGroup(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
