package com.softserve.itacademy.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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

}
