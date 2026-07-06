package com.irfan.mubasyir.rabbitmq.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldCreateUserWithNoArgsConstructor() {
        User user = new User();
        user.setId(1);
        user.setFirstName("John");
        user.setLastName("Doe");

        assertEquals(1, user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
    }

    @Test
    void shouldCreateUserWithAllArgsConstructor() {
        User user = new User(1, "John", "Doe");

        assertEquals(1, user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
    }

    @Test
    void shouldReturnCorrectToString() {
        User user = new User(1, "John", "Doe");
        String result = user.toString();

        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("firstName=John"));
        assertTrue(result.contains("lastName=Doe"));
    }

    @Test
    void shouldHaveCorrectEqualsAndHashCode() {
        User user1 = new User(1, "John", "Doe");
        User user2 = new User(1, "John", "Doe");
        User user3 = new User(2, "Jane", "Smith");

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1, user3);
    }

    @Test
    void shouldHandleEqualsWithNull() {
        User user = new User(1, "John", "Doe");
        assertNotEquals(null, user);
    }

    @Test
    void shouldHandleEqualsWithDifferentType() {
        User user = new User(1, "John", "Doe");
        assertNotEquals("string", user);
    }

    @Test
    void shouldHandleEqualsWithSameObject() {
        User user = new User(1, "John", "Doe");
        assertEquals(user, user);
    }
}
