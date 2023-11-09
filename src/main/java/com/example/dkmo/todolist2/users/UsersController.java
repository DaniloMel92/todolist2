package com.example.dkmo.todolist2.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UsersController {
    @Autowired
    private UsersRepository usersRepository;
        @PostMapping("/")
        public ResponseEntity<?> insert(@RequestBody Users users){

          var passHash = BCrypt.withDefaults().hashToString(12, users.getPassword().toCharArray());
          users.setPassword(passHash);
           Users user = usersRepository.save(users);
      return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @GetMapping
    public ResponseEntity<List<Users>> getAll(){
            var user = usersRepository.findAll();
            return ResponseEntity.status(200).body(user);
    }
  }

