package com.example.tasksmanagementsystems.Repository;

import com.example.tasksmanagementsystems.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
