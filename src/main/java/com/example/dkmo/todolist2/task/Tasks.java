package com.example.dkmo.todolist2.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity(name="Tb_tasks")

public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID Id;

    private UUID idUser;
    @Column(length = 15)
    private String title;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;
    @CreationTimestamp
    private LocalDateTime createdAT;
    
    public void setTitle(String title)throws Exception{
        this.title=title;
        if(this.title.length()>15){
          throw new Exception("O campo title nao pode exceder 15 caracteres");
        }
    }
}
