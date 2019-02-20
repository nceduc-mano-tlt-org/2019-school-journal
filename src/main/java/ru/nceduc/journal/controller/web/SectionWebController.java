package ru.nceduc.journal.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.entity.Section;
import ru.nceduc.journal.service.GroupService;
import ru.nceduc.journal.service.SectionService;

import java.util.List;

@Controller
@RequestMapping("section/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SectionWebController {

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
}
