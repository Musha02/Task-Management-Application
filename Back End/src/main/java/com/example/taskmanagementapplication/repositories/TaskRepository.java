package com.example.taskmanagementapplication.repositories;

import com.example.taskmanagementapplication.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
