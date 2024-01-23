package com.example.taskmanagementapplication.services;

import com.example.taskmanagementapplication.models.Task;
import com.example.taskmanagementapplication.repositories.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService service;

    @Autowired
    private TaskRepository repository;

    @AfterEach
    void deleteAllItems() {
        repository.deleteAll();
    }


    @Test
    @DisplayName("find a given Task by its Id")
    void findATodoItemById() {
        // find a specific Task by its id
        Task item = new Task();
        item.setDescription("todo item1");
        item.setIsComplete(false);

        item = service.save(item);

        Optional<Task> testItem = service.getById(item.getId());
        assertEquals(testItem.isPresent(), true);
        assertEquals(testItem.get().getId(), item.getId());
    }

    @Test
    void getAllTodoItems() {

        Task item1 = new Task();
        item1.setDescription("todo item1");
        item1.setIsComplete(false);

        item1 = service.save(item1);

        Task item2 = new Task();
        item2.setDescription("todo item1");
        item2.setIsComplete(false);

        item2 = service.save(item2);

        Iterable<Task> items = service.getAll();
        List<Task> list = new ArrayList<>();
        items.iterator().forEachRemaining(list::add);
        assertNotEquals(list.size(), 0);
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), item1.getId());
        assertEquals(list.get(1).getId(), item2.getId());
    }

    @Test
    void savingAValidTodoItemSucceeds() {
        Task item = new Task();
        item.setDescription("todo item1");
        item.setIsComplete(false);

        item = service.save(item);
        assertNotEquals(item.getId(), null);
    }

    @Test
    void savingAnInvalidTodoItemFails() {

        Task item = new Task();
        Exception exception = assertThrows(Exception.class, () -> service.save(item));
        assertEquals("Could not commit JPA transaction", exception.getMessage());
    }

    @Test
    void deletingAValidTodoItemSucceeds() {
        Task item = new Task();
        item.setDescription("todo item1");
        item.setIsComplete(false);

        item = service.save(item);
        service.delete(item);

        Iterable<Task> items = service.getAll();
        List<Task> list = new ArrayList<>();
        items.iterator().forEachRemaining(list::add);
        assertEquals(list.size(), 0);
    }
}