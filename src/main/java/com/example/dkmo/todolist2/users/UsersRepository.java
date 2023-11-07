package com.example.dkmo.todolist2.users;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users,UUID>{
Users findByUsername(String username);
}
