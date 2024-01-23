package com.example.taskmanagementapplication.services;

import com.example.taskmanagementapplication.models.Task;
import com.example.taskmanagementapplication.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Optional<Task> getById(Long id) {
        return taskRepository.findById(id);
    }

    public Iterable<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task save(Task task) {
        if (task.getId() == null) {
            task.setCreatedAt(Instant.now());
        }
        task.setUpdatedAt(Instant.now());
        return taskRepository.save(task);
    }

    public void delete(Task task) {
        taskRepository.delete(task);
    }

}
