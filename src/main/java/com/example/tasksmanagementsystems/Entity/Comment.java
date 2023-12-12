package com.example.tasksmanagementsystems.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "task_Id")
    private Long taskId;

    @ManyToOne
    @JoinColumn(name = "task_Id", insertable = false, updatable = false)
    @JsonIgnore
    private Task task;
}
