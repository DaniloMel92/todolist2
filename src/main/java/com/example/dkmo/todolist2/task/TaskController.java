package com.example.dkmo.todolist2.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dkmo.todolist2.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @PostMapping("/")
    public ResponseEntity<?> insertTask(@RequestBody Tasks tasks,HttpServletRequest httpServletRequest){ 
         var id = httpServletRequest.getAttribute("idUser");
         tasks.setIdUser((UUID) id);
         var currentDate = LocalDateTime.now();
         if(currentDate.isAfter(tasks.getStartAt())||currentDate.isAfter(tasks.getEndAt())||tasks.getEndAt().isBefore(tasks.getStartAt())){
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("não é possivel validar hora.");
         }
           var task = taskRepository.save(tasks);
      return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }
    @GetMapping("/")
    public ResponseEntity<List<Tasks>> getAll(HttpServletRequest request){
       var id = request.getAttribute("idUser");
       var userTask = taskRepository.findByIdUser((UUID)id);
     return ResponseEntity.status(200).body(userTask);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> alterTask(@RequestBody Tasks tasks, @PathVariable UUID id, HttpServletRequest request){
     var task = taskRepository.findById(id).orElse(null);
    var idUser = request.getAttribute("idUser");
      if(task==null){
     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não existe"); 
     }
         if(!task.getIdUser().equals(idUser)){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro, esta tarefa não pertence a este usuário");
    }
    Utils.copyNonNullProperties(tasks, task);
    taskRepository.save(task);
    return ResponseEntity.status(200).body(task);

    }
    
}
