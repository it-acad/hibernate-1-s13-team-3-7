package com.softserve.itacademy.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ToDoTests {
    private static User owner;
    private static ToDo validToDo;

    @BeforeAll
    static void init() {
        owner = new User();
    }

    @Test
    void constraintViolationOnEmptyRoleName() {
        ToDo emptyTitleToDo = new ToDo();
        emptyTitleToDo.setTitle("");
        emptyTitleToDo.setOwner(owner);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(emptyTitleToDo);
        assertEquals(1, violations.size());
    }

    @Test
    void constraintViolationOnNullOwner() {
        ToDo toDoWithoutOwner = new ToDo();
        toDoWithoutOwner.setTitle("Title");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(toDoWithoutOwner);
        assertEquals(1, violations.size());
    }

    @Test
    void testToDoSetterGetter() {
        User owner = new User();
        ToDo toDo = new ToDo();
        List<Task> taskList = new ArrayList<>();
        Set<User> collaborators = new HashSet<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        String title = "Title";
        long id = 1;
        toDo.setId(id);
        toDo.setTitle(title);
        toDo.setTasks(taskList);
        toDo.setOwner(owner);
        toDo.setCollaborators(collaborators);
        toDo.setCreatedAt(localDateTime);
        Assertions.assertSame(id,toDo.getId());
        Assertions.assertSame(title,toDo.getTitle());
        Assertions.assertSame(taskList,toDo.getTasks());
        Assertions.assertSame(owner,toDo.getOwner());
        Assertions.assertSame(collaborators,toDo.getCollaborators());
        Assertions.assertSame(localDateTime,toDo.getCreatedAt());
    }

    @Test
    void testToDoEqualsHashCode() {
        ToDo todo = new ToDo();
        todo.setTitle("s");
        todo.setOwner(new User());
        ToDo todo1 = new ToDo();
        todo1.setTitle("s");
        todo1.setOwner(new User());
        assertEquals(todo,todo1);
        assertEquals(todo.hashCode(),todo1.hashCode());
    }

}
