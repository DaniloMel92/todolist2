package com.example.dkmo.todolist2.task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
@DataJpaTest
class TaskControllerTest {
@Autowired
    TaskRepository taskRepository;
@Test
    void TestControllerOfTasks()throws Exception{
Tasks test = createdTask();
taskRepository.save(test);
assertThat(test.getTitle()).isNotNull();
assertThat(test.getDescription()).isNotNull();
}
private Tasks createdTask()throws Exception{
    Tasks tasks = new Tasks();
    tasks.setTitle("Test of task");
    tasks.setDescription("This task is a test that have how objective to save a task");
    return tasks;
}
@Test
void TestFindAllTaskInDatabases()throws Exception{
    var tasks = new Tasks();
    var tasks2 = new Tasks();
    tasks.setTitle("Test show all");
    tasks.setDescription("The test search all tasks record in the database");
    tasks2.setTitle("Other object");
    tasks2.setDescription("a other object that to test if to get all the tasks");
    Tasks test1 = taskRepository.save(tasks);
    Tasks test2 = taskRepository.save(tasks2);
    List<Tasks> list = new ArrayList<>();
    list.add(test1);
    list.add(test2);
    List<Tasks> testResult = taskRepository.findAll();
    assertThat(testResult).contains(test1,list.get(0)).contains(test2,list.get(1));
}
}