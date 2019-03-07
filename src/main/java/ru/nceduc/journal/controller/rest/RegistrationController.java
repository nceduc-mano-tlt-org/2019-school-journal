package ru.nceduc.journal.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.nceduc.journal.dto.UserDTO;
import ru.nceduc.journal.entity.Role;
import ru.nceduc.journal.service.impl.UserServiceImpl;

import java.util.Collections;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    private UserServiceImpl service;

    @GetMapping(value = "")
    public String registration(){
        return "registration";
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "")
    public String createUser(UserDTO user){
        Boolean expected = service.getByName(user.getUsername());
        if (expected == true){
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        service.create(user);
        return "login";
    }
}
