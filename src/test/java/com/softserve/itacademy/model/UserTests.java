package com.softserve.itacademy.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;

@SpringBootTest
public class UserTests {

    private static Role mentorRole;
    private static Role traineeRole;
    private static User validUser;

    @BeforeAll
    static void init(){
        mentorRole = new Role();
        mentorRole.setName("MENTOR");
        traineeRole = new Role();
        traineeRole.setName("TRAINEE");
        validUser  = new User();
        validUser.setEmail("valid@cv.edu.ua");
        validUser.setFirstName("Valid-Name");
        validUser.setLastName("Valid-Name");
        validUser.setPassword("qwQW12!@");
        validUser.setRole(traineeRole);
    }

    @Test
    void userWithValidEmail() {
        User user = new User();
        user.setEmail("rty5@i.ua");
        user.setFirstName("Valid-Name");
        user.setLastName("Valid-Name");
        user.setPassword("qwQW12!@");
        user.setRole(traineeRole);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    void createValidUser() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(0, violations.size());
    }

    @Test
    void testUserSetterGetter() {
        User user = new User();
        Role role = new Role();
        List<ToDo> toDos = new ArrayList<>();
        Set<ToDo> toDoSet = new HashSet<>();
        int id = 1;
        String name = "Name";
        String lastName = "Lastname";
        String password = "1234dfgh";
        String email = "email@gmail.com";
        user.setId(id);
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(role);
        user.setToDoList(toDos);
        user.setCollaboratorsToDoList(toDoSet);
        Assertions.assertSame(name,user.getFirstName());
        Assertions.assertSame(lastName,user.getLastName());
        Assertions.assertSame(password,user.getPassword());
        Assertions.assertSame(email,user.getEmail());
        Assertions.assertSame(role,user.getRole());
        Assertions.assertSame(toDos,user.getToDoList());
        Assertions.assertSame(toDoSet,user.getCollaboratorsToDoList());
        Assertions.assertSame(id,user.getId());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidEmailUser")
    void constraintViolationInvalid(String input, String errorValue) {
        User user = new User();
        user.setEmail(input);
        user.setFirstName("Valid-Name");
        user.setLastName("Valid-Name");
        user.setPassword("qwQW12!@");
        user.setRole(traineeRole);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidEmailUser(){
        return Stream.of(
                Arguments.of("invalidEmail", "invalidEmail"),
                Arguments.of("email@", "email@"),
                Arguments.of("", ""),
                Arguments.of("invalid", "invalid")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidFirstNameUser")
    void constraintViolationInvalidFirstName(String input, String errorValue) {
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(input);
        user.setLastName("Valid-Name");
        user.setPassword("qwQW12!@");
        user.setRole(traineeRole);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidFirstNameUser(){
        return Stream.of(
                Arguments.of("invalid", "invalid"),
                Arguments.of("Invalid-", "Invalid-"),
                Arguments.of("Invalid-invalid", "Invalid-invalid"),
                Arguments.of("", ""),
                Arguments.of("IvaN", "IvaN")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPassword")
    void constraintViolationInvalidPassword(String input, String errorValue) {
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName("Valid-Name");
        user.setLastName("Valid-Name");
        user.setPassword(input);
        user.setRole(traineeRole);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidPassword(){
        return Stream.of(
                Arguments.of("123kll(l", "123kll(l"),
                Arguments.of("_1134==dJk", "_1134==dJk"),
                Arguments.of("1ADas<?24", "1ADas<?24"),
                Arguments.of("1ADas<24", "1ADas<24"),
                Arguments.of("1ADas>*24", "1ADas>*24"),
                Arguments.of("1ADas?24", "1ADas?24"),
                Arguments.of(" lklk78", " lklk78"),
                Arguments.of("", "")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidLastNameUser")
    void constraintViolationInvalidLastName(String input, String errorValue) {
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName("Valid-Name");
        user.setLastName(input);
        user.setPassword("qwQW12!@");
        user.setRole(traineeRole);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidLastNameUser(){
        return Stream.of(
                Arguments.of("invalid", "invalid"),
                Arguments.of("Invalid-", "Invalid-"),
                Arguments.of("Invalid-invalid", "Invalid-invalid"),
                Arguments.of("", ""),
                Arguments.of("KovaLenko", "KovaLenko")
        );
    }

    @Test
    void testUserEqualsHashCode() {
        User user = new User();
        User user1 = new User();
        assertEquals(user,user1);
        assertEquals(user.hashCode(),user1.hashCode());
    }

}
