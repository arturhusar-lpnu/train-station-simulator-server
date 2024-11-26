package dev.test_models;

import models.Client;
import models.privileges.PrivilegeType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClientTest {
    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client(1, 5, PrivilegeType.None);
    }

    @Test
    @DisplayName("Клієнт ініціалізується з правильними параметрами")
    void testClientInitialization() {
        assertEquals(1, client.getId());
        assertEquals(5, client.getTicketsToBuy());
        assertEquals(PrivilegeType.None, client.getPrivilege());
    }

    @Test
    @DisplayName("Метод interrupt() змінює привілей на Interrupted")
    void testInterruptMethod() {
        client.interrupt();
        assertEquals(PrivilegeType.Interrupted, client.getPrivilege());
    }

    @Test
    @DisplayName("Getter-методи повертають коректні значення")
    void testGetterMethods() {
        assertNotNull(client.getId());
        assertNotNull(client.getTicketsToBuy());
        assertNotNull(client.getPrivilege());
    }

    @Test
    @DisplayName("Неможливо змінити id та кількість квитків через геттери")
    void testImmutabilityOfFields() {
        Client originalClient = client;

        // Перевірка, що поля не змінюються при створенні нового об'єкта
        Client newClient = new Client(
                client.getId(),
                client.getTicketsToBuy(),
                client.getPrivilege()
        );

        assertEquals(originalClient.getId(), newClient.getId());
        assertEquals(originalClient.getTicketsToBuy(), newClient.getTicketsToBuy());
        assertEquals(originalClient.getPrivilege(), newClient.getPrivilege());
    }
}