package com.example.dkmo.todolist2.users;
import com.example.dkmo.todolist2.Todolist2Application;
import jakarta.persistence.Column;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.dkmo.todolist2.users.Users;
import com.example.dkmo.todolist2.users.UsersRepository;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Todolist2Application.class,properties = "test")
@AutoConfigureMockMvc
public class UsersControllerTest {
    @MockBean
    UsersRepository usersRepository;
    @Autowired
    public MockMvc mockMvc;

    Users user = new Users();
@Test
    public void showAllUsers()throws Exception {
   user.setName("danilo");
   user.setUsername("danilo92");
   when(usersRepository.findAll()).thenReturn(List.of(user));
   mockMvc.perform(get("/users"))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(content().json("[{name:danilo,password:null,username:danilo92}]"));
    }
@Test
    public void insertAUsersOnTheDatabase()throws Exception{
    user.setUsername("danilo97");
    user.setPassword("danilo92");
    user.setName("danilo");
    when(usersRepository.save(any())).thenReturn(user);
    mockMvc.perform(post("/users/")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\":\"danilo97\",\"password\":\"danilo92\",\"name\":\"danilo\"}")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value("danilo97"))
            .andExpect(jsonPath("$.password").value("danilo92"))
            .andExpect(jsonPath("$.name").value("danilo"));


}

    }
