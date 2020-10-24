package com.softserve.itacademy.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TaskTests {

    @ParameterizedTest
    @MethodSource("provideInvalidName")
    void constraintViolationInvalidName(String input, String errorValue) {
        Task task = new Task();
        task.setName(input);
        task.setPriority(Priority.HIGH);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidName(){
        return Stream.of(
                Arguments.of("h2", "h2"),
                Arguments.of("In this message over 20 symbols", "In this message over 20 symbols"),
                Arguments.of("", "")
        );
    }

    @Test
    void testTaskSetterGetter() {
        Task task = new Task();
        State state = new State();
        ToDo toDo = new ToDo();
        long id = 1;
        String name = "Task";
        Priority priority = Priority.HIGH;
        task.setName(name);
        task.setPriority(priority);
        task.setId(id);
        task.setState(state);
        task.setTodo(toDo);
        Assertions.assertSame(id,task.getId());
        Assertions.assertSame(name,task.getName());
        Assertions.assertSame(priority,task.getPriority());
        Assertions.assertSame(state,task.getState());
        Assertions.assertSame(toDo,task.getTodo());
    }

    @Test
    void testTaskEqualsHashCode() {
        Task task = new Task();
        Task task1 = new Task();
        assertEquals(task,task1);
        assertEquals(task.hashCode(),task1.hashCode());
    }

}
