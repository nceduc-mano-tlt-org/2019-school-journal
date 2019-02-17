package ru.nceduc.journal.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nceduc.journal.entity.User;
import ru.nceduc.journal.service.impl.UserServiceImpl;

@Controller
@RequestMapping(value = "/")
public class ControllerMain {

    @Autowired
    UserServiceImpl service;

    @GetMapping(value = "/hello")
    public String getHello(Model model){
        User user = service.getCurrentUsername();
        model.addAttribute("username",user.getUsername());
        model.addAttribute("password",user.getPassword());
        return "hello";
    }

    @GetMapping(value = "/")
    public String getHome(){
        return "home";
    }
}
