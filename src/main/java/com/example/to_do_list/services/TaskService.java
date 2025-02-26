package com.example.to_do_list.services;

import com.example.to_do_list.dtos.Task;
import com.example.to_do_list.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public void saveTask(Task task) {
        if(task.getCollaborators() != null){
            task.getCollaborators().forEach(chore -> {
                chore.setTasks(task);
                chore.setId(null);
            });
        }

        if(task.getAuthor() != null){
            taskRepository.save(task);
        }




        System.out.println("Tarea guardada con éxito" + task.getCollaborators());
    }



}
