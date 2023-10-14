package com.example.dkmo.todolist2.task;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tasks,UUID> {
    List<Tasks>findByIdUser(UUID idUser);
}
