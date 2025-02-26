package com.example.to_do_list.consumer;

import com.example.to_do_list.dtos.Task;
import com.example.to_do_list.services.TaskService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TodoListConsumer {
    private final TaskService taskService;

    public TodoListConsumer(TaskService taskService) {
        this.taskService = taskService;
    }

    @RabbitListener(queues = "to-do-list_queue")
    public void receiveMessage(Task task) {
        System.out.println("Recibido mensaje de " + task.getAuthor().getName());
        taskService.saveTask(task);
    }


}
