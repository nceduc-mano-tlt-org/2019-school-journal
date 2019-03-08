/*
package ru.nceduc.journal.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.nceduc.journal.dto.GroupDTO;
import ru.nceduc.journal.dto.SectionDTO;
import ru.nceduc.journal.service.GroupService;
import ru.nceduc.journal.service.SectionService;

import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SectionWebController {

    private final GroupService groupService;
    private final SectionService sectionService;

    @GetMapping("/sections")
    public String showAllSectionsPage(Model model) {
        List<SectionDTO> sectionsDTO = sectionService.getAll();
        model.addAttribute("sections", sectionsDTO);
        return "sections";
    }

    @GetMapping("/section/{id}")
    public String showSectionPage(Model model, @PathVariable String id) {
        List<GroupDTO> groupsDTO = groupService.getAllBySectionId(id);
        SectionDTO sectionDTO = sectionService.get(id);
        model.addAttribute("groups", groupsDTO);
        model.addAttribute("sectionId", id);
        model.addAttribute("sectionName", sectionDTO.getName());
        return "section";
    }
}
*/
