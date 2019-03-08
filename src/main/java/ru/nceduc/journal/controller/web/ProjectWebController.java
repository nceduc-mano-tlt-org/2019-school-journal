/*
package ru.nceduc.journal.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nceduc.journal.dto.ProjectDTO;
import ru.nceduc.journal.entity.UserEntity;
import ru.nceduc.journal.service.impl.ProjectServiceImpl;
import ru.nceduc.journal.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/project/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectWebController {

    private final ProjectServiceImpl serviceProject;
    private final UserServiceImpl serviceUser;

    @GetMapping("")
    public String getProjectPage(Model model) {
        UserEntity userEntity = serviceUser.getCurrentUsername();
        ProjectDTO project = serviceProject.get(userEntity.getProject().getId());
        model.addAttribute("projectName", project.getName());
        //TODO
        return "project";
    }
}
*/
