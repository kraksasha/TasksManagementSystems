package com.example.tasksmanagementsystems.Service;

import com.example.tasksmanagementsystems.Entity.Comment;
import com.example.tasksmanagementsystems.Entity.User;
import com.example.tasksmanagementsystems.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;


    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void createNewComment(Comment comment){
        commentRepository.save(comment);
    }
}
