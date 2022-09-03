package ru.kazemirov.gateway.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kazemirov.gateway.domain.Role;
import ru.kazemirov.gateway.domain.User;
import ru.kazemirov.gateway.service.UserService;

import java.util.Collections;
import java.util.List;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;
    @PostMapping("/registration/{role}")
    @ResponseBody
    public String registration(@PathVariable Role role, @RequestBody String registrationData) throws JsonProcessingException {
        userService.addUser(role, registrationData);
        return "/home";
    }
    @GetMapping("/get/users")
    @ResponseBody
    public List<User> getUsers (){
        return userService.getAllUsers();
    }
    @GetMapping("/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration.html");
        return modelAndView;
    }
}
