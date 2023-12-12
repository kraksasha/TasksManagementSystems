package com.example.tasksmanagementsystems.Controller;

import com.example.tasksmanagementsystems.Entity.Comment;
import com.example.tasksmanagementsystems.Service.CommentService;
import com.example.tasksmanagementsystems.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/create/{id}")
    public ResponseEntity<?> createComment(@RequestBody Comment comment, @PathVariable(name = "id") Long id){
        comment.setTaskId(id);
        commentService.createNewComment(comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
