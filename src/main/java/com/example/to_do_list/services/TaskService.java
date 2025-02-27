package com.example.to_do_list.services;

import com.example.to_do_list.dtos.Task;
import com.example.to_do_list.dtos.User;
import com.example.to_do_list.repository.TaskRepository;
import com.example.to_do_list.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveTask(Task task) {
        if (task.getCollaborators() != null) {
            task.getCollaborators().forEach(collaborator -> {
                if (collaborator.getId() == null) {
                    userRepository.save(collaborator);
                }
                if (collaborator.getTasks() == null) {
                    collaborator.setTasks(new ArrayList<>());
                }
                collaborator.getTasks().add(task);
            });
        }

        if (task.getAuthor() != null && task.getAuthor().getId() == null) {
            userRepository.save(task.getAuthor());
        }

        taskRepository.save(task);
        System.out.println("Task saved successfully: " + task.getCollaborators());
    }
}