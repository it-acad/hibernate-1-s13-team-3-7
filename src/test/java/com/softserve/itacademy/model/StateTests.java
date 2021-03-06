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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class StateTests {

    @ParameterizedTest
    @MethodSource("provideInvalidStateName")
    void constraintViolationInvalid(String input, String errorValue) {
        State state = new State();
        state.setName(input);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(state);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidStateName() {
        return Stream.of(
                Arguments.of("invalid.Name", "invalid.Name"),
                Arguments.of("invalid,Name", "invalid,Name"),
                Arguments.of("", ""),
                Arguments.of("invalid@", "invalid@"),
                Arguments.of("In this message over 20 symbols", "In this message over 20 symbols")

        );
    }

    @Test
    void testStateSetterGetter() {
        State state = new State();
        int id = 1;
        String name = "State";
        List<Task> taskList = new ArrayList<>();
        state.setName(name);
        state.setId(id);
        state.setTaskList(taskList);
        Assertions.assertSame(id,state.getId());
        Assertions.assertSame(name,state.getName());
        Assertions.assertSame(taskList,state.getTaskList());
    }

    @Test
    void testStateEqualsHashCode() {
        State state = new State();
        State state1 = new State();
        assertEquals(state, state1);
        assertEquals(state.hashCode(), state1.hashCode());
    }

}
