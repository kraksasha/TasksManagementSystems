package com.example.tasksmanagementsystems.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "head")
    private String head;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "priority")
    private String priority;

    @Column(name = "author")
    private String author;

    @Column(name = "executor")
    private String executor;

    @Column(name = "user_Id")
    private Long userId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_Id", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

}
