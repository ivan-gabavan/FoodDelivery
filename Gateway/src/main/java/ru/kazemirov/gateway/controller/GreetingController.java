package ru.kazemirov.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GreetingController {
    @GetMapping({"/", "/home"})
    public ModelAndView greetingPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home.html");
        return (modelAndView);
    }
}
