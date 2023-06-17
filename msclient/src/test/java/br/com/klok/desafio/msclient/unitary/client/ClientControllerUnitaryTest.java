package br.com.klok.desafio.msclient.unitary.client;

import br.com.klok.desafio.msclient.business.service.ClientService;
import br.com.klok.desafio.msclient.model.entity.ClientModel;
import br.com.klok.desafio.msclient.presetation.controller.ClientController;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import br.com.klok.desafio.msclient.presetation.dto.ClientDto;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ClientControllerUnitaryTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    @Tag("create_method")
    @DisplayName("Test create ClientController")
    public void testCreateClient() {
        var clientDtoMock = mock(ClientDto.class);
        var clientModelMock = mock(ClientModel.class);

        when(clientService.saveClient(clientDtoMock)).thenReturn(clientModelMock);

        ResponseEntity<ClientModel> response = clientController.createClient(clientDtoMock);

        verify(clientService).saveClient(clientDtoMock);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(clientModelMock, response.getBody());
    }

    @Test
    @Order(2)
    @Tag("getAll_method")
    @DisplayName("Test find all ClientController")
    public void testGetAll() {
        List<ClientModel> clients = new ArrayList<>();

        when(clientService.getAllClient()).thenReturn(clients);

        ResponseEntity<List<ClientModel>> response = clientController.getAllClients();

        verify(clientService).getAllClient();
        Assertions. assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(clients, response.getBody());
    }

    @Test
    @Order(3)
    @Tag("getById_method")
    @DisplayName("Test find by id ClientController")
    public void testFindById() {
        String uuid = "abc123";
        var clientModelMock = mock(ClientModel.class);

        when(clientService.getClientById(uuid)).thenReturn(clientModelMock);

        ResponseEntity<ClientModel> response = clientController.getClientById(uuid);

        verify(clientService).getClientById(uuid);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(clientModelMock, response.getBody());
    }

    @Test
    @Order(4)
    @Tag("getByEmail_method")
    @DisplayName("Test find by email ClientController")
    public void testFindByEmail() {
        String email = "abc123@email.com";
        var clientModelMock = mock(ClientModel.class);

        when(clientService.getClientByEmail(email)).thenReturn(clientModelMock);

        ResponseEntity<ClientModel> response = clientController.getClientByEmail(email);

        verify(clientService).getClientByEmail(email);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(clientModelMock, response.getBody());
    }

    @Test
    @Order(5)
    @Tag("update_method")
    @DisplayName("Test update ClientController")
    public void testUpdateClient() {
        String uuid = "abc123";
        var clientDtoMock = mock(ClientDto.class);
        var updatedClientMock = mock(ClientModel.class);

        when(clientService.updateClient(uuid, clientDtoMock)).thenReturn(updatedClientMock);

        ResponseEntity<ClientModel> response = clientController.updateClientById(uuid, clientDtoMock);

        verify(clientService).updateClient(uuid, clientDtoMock);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(updatedClientMock, response.getBody());
    }

    @Test
    @Order(6)
    @Tag("delete_method")
    @DisplayName("Test delete ClientController")
    public void testDeleteClient() {
        String uuid = "abc123";

        var clientModel = mock(ClientModel.class);
        when(clientModel.getUuid()).thenReturn(uuid);

        when(clientService.getClientById(uuid)).thenReturn(clientModel);
        ResponseEntity<String> response = clientController.deleteClientById(uuid);

        verify(clientService).deleteClientById(uuid);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Sucessofully deleted!", response.getBody());
    }
}