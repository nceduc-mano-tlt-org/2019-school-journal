package ru.nceduc.journal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nceduc.journal.entity.Role;
import ru.nceduc.journal.entity.User;
import ru.nceduc.journal.service.impl.UserServiceImpl;

import java.util.Collections;

@Controller
@RequestMapping(value = "/api/v1/registration")
public class ControllerRegistration {

    @Autowired
    private UserServiceImpl service;

    @GetMapping(value = "")
    public String registration(){
        return "registration";
    }

    @PostMapping(value = "")
    public String createUser(User user){
        User expected = service.getByName(user.getUsername());
        if (expected != null){
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        service.create(user);
        return"redirect:/login";
    }
}
