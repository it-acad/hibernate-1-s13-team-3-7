package com.softserve.itacademy.model;

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
}
