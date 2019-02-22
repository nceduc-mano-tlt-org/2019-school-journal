package ru.nceduc.journal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.SectionDTO;
import ru.nceduc.journal.service.SectionService;

@RestController
@RequestMapping("section/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SectionController {

    private final SectionService sectionService;

    @PostMapping("create")
    public ResponseEntity<SectionDTO> createSection(SectionDTO sectionDTO) {

        // TODO  ---  Need project id from context

        SectionDTO createdSection = sectionService.create(sectionDTO);
        return new ResponseEntity<>(createdSection, HttpStatus.CREATED);
    }

    @PatchMapping("patch")
    public ResponseEntity<SectionDTO> patchSection(SectionDTO sectionDTO) {
        SectionDTO patchedSection = sectionService.patch(sectionDTO);
        return new ResponseEntity<>(patchedSection, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<SectionDTO> updateSection(SectionDTO sectionDTO) {
        SectionDTO updatedSection = sectionService.update(sectionDTO);
        return new ResponseEntity<>(updatedSection, HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<SectionDTO> deleteSection(String id) {
        sectionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
