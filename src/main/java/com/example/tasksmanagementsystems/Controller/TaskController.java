package com.example.tasksmanagementsystems.Controller;

import com.example.tasksmanagementsystems.Entity.Task;
import com.example.tasksmanagementsystems.Service.TaskService;
import com.example.tasksmanagementsystems.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private TaskService taskService;
    private UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createTask(@RequestBody Task task){
        Long userId = userService.getUserNow().getId();
        task.setUserId(userId);
        taskService.createNewTask(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/mytasks")
    public ResponseEntity<List<Task>> getTasks(){
        List<Task> list = userService.getMyTasks();
        if (list != null && list.size() != 0){
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/mytask/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(name = "id") Long id){
        Task task = taskService.getTask(id);
        if (task != null){
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable(name = "id") Long id){
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateTask(@PathVariable(name = "id") Long id, @RequestBody Task task){
        taskService.updateTask(task,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
