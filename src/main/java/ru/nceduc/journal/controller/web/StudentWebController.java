package ru.nceduc.journal.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nceduc.journal.dto.StudentDTO;
import ru.nceduc.journal.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentWebController {
    private final StudentService studentService;

    @GetMapping("{id}")
    public String showPage(Model model, @PathVariable String id){
        List<StudentDTO> studentDTOS = studentService.getAll();

        //TODO: Finish

        return "student";
    }
}
