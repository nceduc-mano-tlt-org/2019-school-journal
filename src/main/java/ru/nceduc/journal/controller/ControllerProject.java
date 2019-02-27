package ru.nceduc.journal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nceduc.journal.service.impl.ProjectServiceImpl;

@Controller
@RequestMapping(value = "/api/v1/project")
public class ControllerProject {

    @Autowired
    private ProjectServiceImpl service;

    @GetMapping(value = "")
    public String getProjectForm(){
        return "project";
    }
}
