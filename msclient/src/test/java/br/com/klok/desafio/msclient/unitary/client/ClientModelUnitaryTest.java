package br.com.klok.desafio.msclient.unitary.client;

import br.com.klok.desafio.msclient.model.entity.ClientModel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClientModelUnitaryTest {

    private static ClientModel client;

    @BeforeAll
    @Test
    public static void setUp(){
        client = new ClientModel();
    }

    @Test
    @Order(1)
    @DisplayName("Check UUID Create a Client")
    public void checkUUIDCreate(){
        assertNotNull(client.getUuid());
    }

    @Test
    @Order(2)
    @DisplayName("Getters and setters")
    public void checkGetAndSet(){

        client.setName("Nome Teste");
        client.setEmail("teste@email.com");

        assertEquals("Nome Teste", client.getName());
        assertEquals("teste@email.com", client.getEmail());

    }

}
