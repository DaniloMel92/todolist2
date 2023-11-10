package com.example.dkmo.todolist2.users;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UsersRepositoryTest {
    @Autowired
    UsersRepository usersRepository;
 @Test
    void savingUsersInDatabasesExpectSuccessful(){
Users users =new Users();
        users.setName("danilo");
        users.setUsername("danilo92");
        users.setPassword("danilo92");
        usersRepository.save(users);

    }
    @Test
    void whenTheMethodGetToCallResponseWithSuccessful(){
     Users users = createUsers();
     Users userCreated = usersRepository.save(users);
     assertThat(userCreated).isNotNull();
     assertThat(userCreated.getId()).isNotNull();
     assertThat(userCreated.getUsername()).isNotNull();
    }
    private Users createUsers(){
     var users = new Users();
     users.setName("Danilo");
     users.setUsername("Danilo92");
     users.setPassword("danilo92");
     return users;
    }
}