package ru.nceduc.journal.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.service.impl.UserServiceImpl;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationWebController {

    @Autowired
    private UserServiceImpl service;

    @GetMapping(value = "")
    public String registration(){
        return "registration";
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "")
    public String createUser(UserDTO user){
        service.create(user);
        return "login";
    }
}
