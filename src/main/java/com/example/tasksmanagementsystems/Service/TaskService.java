package com.example.tasksmanagementsystems.Service;

import com.example.tasksmanagementsystems.Entity.Task;
import com.example.tasksmanagementsystems.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {
    private TaskRepository taskRepository;
    private UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public void createNewTask(Task task){
        taskRepository.save(task);
    }

    public Task getTask(Long id){
        return taskRepository.findById(id).get();
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public void updateTask(Task task, Long id){
        Task taskUpdated = taskRepository.findById(id).get();
        taskUpdated.setHead(task.getHead());
        taskUpdated.setDescription(task.getDescription());
        taskUpdated.setStatus(task.getStatus());
        taskUpdated.setPriority(task.getPriority());
        taskUpdated.setAuthor(task.getAuthor());
        taskUpdated.setExecutor(task.getExecutor());
        taskRepository.save(taskUpdated);
    }
}
