package br.com.klok.desafio.msclient.unitary.client;

import br.com.klok.desafio.msclient.business.ClientService;
import br.com.klok.desafio.msclient.business.service.impl.ClientServiceImpl;
import br.com.klok.desafio.msclient.model.entity.ClientModel;
import br.com.klok.desafio.msclient.model.repository.ClientRepository;
import br.com.klok.desafio.msclient.presetation.dto.ClientDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ClientServiceUnitaryTest {

    private static ClientService clientService;
    @MockBean
    private static ClientRepository clientRepository;

    @BeforeAll
    public static void setUp() {
        clientService = new ClientServiceImpl(clientRepository);
    }

    @Test
    @Order(1)
    @Tag("convert_method")
    @DisplayName("Save new client")
    public void saveClient() {

        ClientDto clientDto = new ClientDto();
        ClientModel clientModel = new ClientModel();

        when(clientRepository.save(any(ClientModel.class))).thenReturn(clientModel);

        ClientModel result = clientService.saveClient(clientDto);

        verify(clientRepository).save(any(ClientModel.class));
        assertEquals(clientModel, result);
    }

    @Test
    @Order(2)
    @Tag("convert_method")
    @DisplayName("Converte ClientDTO for ClientModel")
    public void getAllClient() {

    }

    @Test
    @Order(3)
    @Tag("convert_method")
    @DisplayName("Converte ClientDTO for ClientModel")
    public void getClientById(String id) {

    }

    @Test
    @Order(4)
    @Tag("convert_method")
    @DisplayName("Converte ClientDTO for ClientModel")
    public void getClientByEmail(String email) {

    }

    @Test
    @Order(5)
    @Tag("convert_method")
    @DisplayName("Converte ClientDTO for ClientModel")
    public void deleteClientById(String id) {

    }
}
