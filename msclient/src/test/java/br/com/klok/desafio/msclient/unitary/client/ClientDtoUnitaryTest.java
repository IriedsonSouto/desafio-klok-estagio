package br.com.klok.desafio.msclient.unitary.client;

import br.com.klok.desafio.msclient.model.entity.ClientModel;
import br.com.klok.desafio.msclient.presetation.dto.ClientDto;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientDtoUnitaryTest {

    private static ClientDto clientDto;
    private static ClientModel clientMock;


    @BeforeAll
    public static void setup() {
        clientDto = new ClientDto();
        clientMock = mock(ClientModel.class);
    }

    @Test
    @Order(1)
    @Tag("sets_gets_method")
    @DisplayName("Setters and Getters method ClientDTO")
    public void testClientDTOSetterAndGetter() {

        clientDto.setName("Nome Test");
        clientDto.setEmail("teste@example.com");

        assertEquals("Nome Test", clientDto.getName());
        assertEquals("teste@example.com", clientDto.getEmail());
    }

    @Test
    @Order(2)
    @Tag("construct_method")
    @DisplayName("Constructor method ClientDTO by ClientModel")
    public void testClientDTOByClientModel() {

        when(clientMock.getName()).thenReturn("Nome Teste");
        when(clientMock.getEmail()).thenReturn("email@email.com");

        clientDto = new ClientDto(clientMock);

        assertEquals("Nome Teste", clientDto.getName());
        assertEquals("email@email.com", clientDto.getEmail());
    }

    @Test
    @Order(3)
    @Tag("convert_method")
    @DisplayName("Converte ClientDTO for ClientModel")
    public void testClientDTOForClientModel() {

        ClientModel clientModel = ClientDto.convertToModel(clientDto);
        assertInstanceOf(ClientModel.class, clientModel);
    }

}


