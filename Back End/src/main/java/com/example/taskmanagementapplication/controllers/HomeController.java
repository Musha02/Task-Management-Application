package com.example.taskmanagementapplication.controllers;

import org.springframework.stereotype.Controller;
import com.example.taskmanagementapplication.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", taskService.getAll());
        return modelAndView;
    }

}
