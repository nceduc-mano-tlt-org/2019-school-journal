package ru.nceduc.journal.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nceduc.journal.entity.Section;
import ru.nceduc.journal.service.SectionService;

import java.util.List;

@Controller
@RequestMapping("project/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectWebController {

    private final SectionService sectionService;

    @GetMapping("{id}")
    public String showProjectPage(Model model) {
        List<Section> sections = sectionService.getAll();
        model.addAttribute("sections", sections);
        return "project";
    }
}
