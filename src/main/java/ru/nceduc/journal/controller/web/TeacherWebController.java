package ru.nceduc.journal.controller.web;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nceduc.journal.dto.TeacherDTO;
import ru.nceduc.journal.entity.Teacher;
import ru.nceduc.journal.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/teacher")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TeacherWebController {
    private final TeacherService teacherService;

    @GetMapping("{id}")
    public String showPage(Model model, @PathVariable String id){
        List<TeacherDTO> teacherDTOS = teacherService.getAll();

        //TODO: Finish

        return "teacher";
    }
}
