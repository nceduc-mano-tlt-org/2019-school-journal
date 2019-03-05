package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.SectionDTO;
import ru.nceduc.journal.service.ProjectService;
import ru.nceduc.journal.service.SectionService;

import java.util.List;

@RestController
@RequestMapping("api/v1/section")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description="Sections operations", tags = "SECTION-V1")
public class SectionController {

    private final SectionService sectionService;
    private final ProjectService projectService;

    @ApiOperation(value = "Get details of specific section")
    @GetMapping("{id}")
    public ResponseEntity<SectionDTO> getSection(@PathVariable String id) {
        SectionDTO sectionDTO = sectionService.get(id);
        return new ResponseEntity<>(sectionDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all sections")
    @GetMapping
    public ResponseEntity<List<SectionDTO>> getAllSections() {
        List<SectionDTO> sectionsDTO = sectionService.getAll();
        return new ResponseEntity<>(sectionsDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all sections by project id")
    @GetMapping
    public ResponseEntity<List<SectionDTO>> getAllSectionsByProjectId(@PathVariable String projectId) {
        List<SectionDTO> sectionsDTO = sectionService.getAllByProjectId(projectId);
        return new ResponseEntity<>(sectionsDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new section")
    @PostMapping
    public ResponseEntity<SectionDTO> createSection(@RequestBody SectionDTO sectionDTO) {

        // TODO  ---  Need project id from context

        SectionDTO createdSection = sectionService.create(sectionDTO);
        return new ResponseEntity<>(createdSection, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Patch section details")
    @PatchMapping
    public ResponseEntity<SectionDTO> patchSection(@RequestBody SectionDTO sectionDTO) {
        SectionDTO patchedSection = sectionService.patch(sectionDTO);
        return new ResponseEntity<>(patchedSection, HttpStatus.OK);
    }

    @ApiOperation(value = "Update section details")
    @PutMapping
    public ResponseEntity<SectionDTO> updateSection(@RequestBody SectionDTO sectionDTO) {
        SectionDTO updatedSection = sectionService.update(sectionDTO);
        return new ResponseEntity<>(updatedSection, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a section")
    @DeleteMapping("{id}")
    public ResponseEntity<SectionDTO> deleteSection(@PathVariable String id) {
        sectionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
