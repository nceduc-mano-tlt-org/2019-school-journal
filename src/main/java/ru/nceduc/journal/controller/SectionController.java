package ru.nceduc.journal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.SectionDTO;
import ru.nceduc.journal.service.SectionService;

@Controller
@RequestMapping("section/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SectionController {

    private final SectionService sectionService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createSection(SectionDTO sectionDTO) {

        // TODO  ---  Need project id from context

        sectionService.create(sectionDTO);
    }

    @PatchMapping("patch")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchSection(SectionDTO sectionDTO) {
        sectionService.patch(sectionDTO);
    }

    @PutMapping("update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSection(SectionDTO sectionDTO) {
        sectionService.update(sectionDTO);
    }

    @DeleteMapping("delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSection(String id) {
       sectionService.delete(id);

    }

}
