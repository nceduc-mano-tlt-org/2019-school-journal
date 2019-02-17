package ru.nceduc.journal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.entity.Section;
import ru.nceduc.journal.service.GroupService;
import ru.nceduc.journal.service.SectionService;

import java.util.List;

@Controller
@RequestMapping("section/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SectionController {

    private final GroupService groupService;
    private final SectionService sectionService;

    @GetMapping("{id}")
    public String showSectionPage(Model model, @PathVariable String id) {
        List<Group> groups = groupService.getAllBySectionId(id);
        Section section = sectionService.get(id);
        model.addAttribute("groups", groups);
        model.addAttribute("sectionId", id);
        model.addAttribute("sectionName", section.getName());
        return "section";
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createSection(Section section) {

        // TODO  ---  Need project id from context

        sectionService.create(section, "0");
    }

    @PutMapping("update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSection(Section section) {
        sectionService.update(section);
    }

    @DeleteMapping("delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSection(String id) {
       boolean status = sectionService.delete(id);

    }

}
