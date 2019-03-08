package ru.nceduc.journal.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.nceduc.journal.entity.UserEntity;
import ru.nceduc.journal.service.impl.UserServiceImpl;

@Controller
public class MainWebController {

    @Autowired
    UserServiceImpl service;

    @GetMapping(value = "/hello/")
    public String getHello(Model model){
//        UserEntity user = service.getCurrentUsername();
//        if (user.getUsername()!= null) {
//            model.addAttribute("username", user.getUsername());
//            model.addAttribute("password", user.getPassword());
//        }
        return "hello";
    }

/*    @GetMapping(value = "/")
    public String getHome(){
        return "home";
    }*/
}
