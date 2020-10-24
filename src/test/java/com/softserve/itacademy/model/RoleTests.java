package com.softserve.itacademy.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RoleTests {

    @Test
    void constraintViolationOnEmptyRoleName() {
        Role emptyRole = new Role();
        emptyRole.setName("");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Role>> violations = validator.validate(emptyRole);
        assertEquals(1, violations.size());
    }

    @Test
    void testRoleSetterGetter() {
        Role newRole = new Role();
        String role = "ADMIN";
        newRole.setName(role);
        List<User> users = new ArrayList<>();
        newRole.setUsers(users);
        Assertions.assertSame(role,newRole.getName());
        Assertions.assertSame(users,newRole.getUsers());
    }

}
