package com.example.dkmo.todolist2.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
@RestController
@RequestMapping("/users")

public class UsersController {
    @Autowired
    private UsersRepository usersRepository;
        @PostMapping("/")
        public ResponseEntity<?> insert(@RequestBody Users users){
          var usuario = usersRepository.findByUsername(users.getUsername());
          if(usuario!=null){
          return ResponseEntity.status(400).body("Erro, usuário já cadastrado");

          }
          var passHash = BCrypt.withDefaults().hashToString(12, users.getPassword().toCharArray());
          users.setPassword(passHash);
           Users user = usersRepository.save(users);
      return ResponseEntity.status(HttpStatus.OK).body(user);
    }

  }

