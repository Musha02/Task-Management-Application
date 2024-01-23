package com.example.taskmanagementapplication.controllers;

import com.example.taskmanagementapplication.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.taskmanagementapplication.services.TaskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/create-todo")
    public String showCreateForm(Task task) {
        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid Task task, BindingResult result, Model model) {

        Task item = new Task();
        item.setDescription(task.getDescription());
        item.setIsComplete(task.getIsComplete());

        taskService.save(task);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
        Task task = taskService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task id: " + id + " not found"));

        taskService.delete(task);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Task task = taskService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task id: " + id + " not found"));

        model.addAttribute("todo", task);
        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid Task task, BindingResult result, Model model) {

        Task item = taskService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task id: " + id + " not found"));

        item.setIsComplete(task.getIsComplete());
        item.setDescription(task.getDescription());

        taskService.save(item);

        return "redirect:/";
    }
}
