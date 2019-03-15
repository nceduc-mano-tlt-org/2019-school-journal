package ru.nceduc.journal.controller.rest;

import ch.qos.logback.core.joran.spi.ActionException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.SectionDTO;
import ru.nceduc.journal.entity.Section;
import ru.nceduc.journal.service.ProjectService;
import ru.nceduc.journal.service.SectionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/section")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description="Sections operations", tags = "SECTION-V1")
public class SectionController {

    private final SectionService sectionService;
    private final ProjectService projectService;

    @ApiOperation(value = "Get details of specific section")
    @GetMapping("/{id}")
    public ResponseEntity<SectionDTO> getSection(@PathVariable String id) {
        Optional<SectionDTO> optionalDTO = sectionService.get(id);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Get all sections")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<SectionDTO>> getAllSections() {
        return new ResponseEntity<>(sectionService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all sections by project id")
    @GetMapping("/by-project/{projectId}")
    public ResponseEntity<List<SectionDTO>> getAllSectionsByProjectId(@PathVariable String projectId) {
        return new ResponseEntity<>(sectionService.getAllByProjectId(projectId), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new section")
    @PostMapping("/")
    public ResponseEntity<SectionDTO> createSection(@RequestBody SectionDTO sectionDTO) {
        String projectId = projectService.getCurrentProject().getId();
        sectionDTO.setProjectId(projectId);
        Optional<SectionDTO> optionalDTO = sectionService.create(sectionDTO);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation(value = "Update section details")
    @PutMapping("/")
    public ResponseEntity<SectionDTO> updateSection(@RequestBody SectionDTO sectionDTO) {
        Optional<SectionDTO> optionalDTO = sectionService.update(sectionDTO);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation(value = "Patch section details")
    @PatchMapping("/")
    public ResponseEntity<SectionDTO> patchSection(@RequestBody SectionDTO sectionDTO) {
        Optional<SectionDTO> optionalDTO = sectionService.patch(sectionDTO);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation(value = "Delete a section")
    @DeleteMapping("/{id}")
    public ResponseEntity<SectionDTO> deleteSection(@PathVariable String id) {
        sectionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
