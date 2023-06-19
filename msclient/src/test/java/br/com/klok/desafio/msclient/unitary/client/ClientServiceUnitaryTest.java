package br.com.klok.desafio.msclient.unitary.client;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.klok.desafio.msclient.infra.data.SaleDataDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.klok.desafio.msclient.business.service.ClientService;
import br.com.klok.desafio.msclient.business.service.impl.ClientServiceImpl;
import br.com.klok.desafio.msclient.exception.EntityNotFoundException;
import br.com.klok.desafio.msclient.infra.PostRabbitClient;
import br.com.klok.desafio.msclient.model.entity.ClientModel;
import br.com.klok.desafio.msclient.model.repository.ClientRepository;
import br.com.klok.desafio.msclient.presetation.dto.ClientDto;

public class ClientServiceUnitaryTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private PostRabbitClient queuePostRabbitClient;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @Order(1)
    @Tag("save_method")
    @DisplayName("Test Save ClientServiceImpl")
    public void testSave() {

        var clientDto = mock(ClientDto.class);
        var clientModel = mock(ClientModel.class);

        when(clientRepository.save(any(ClientModel.class))).thenReturn(clientModel);

        ClientModel result = clientService.saveClient(clientDto);

        verify(clientRepository).save(any(ClientModel.class));
        assertEquals(clientModel, result);
    }

    @Test
    @Order(2)
    @Tag("getAll_method")
    @DisplayName("Test find all ClientServiceImpl")
    public void testGetAll() {

        List<ClientModel> clientList = new ArrayList<>();
        clientList.add(new ClientModel());

        when(clientRepository.findAll()).thenReturn(clientList);

        List<ClientModel> result = clientService.getAllClient();

        verify(clientRepository).findAll();
        assertEquals(clientList, result);
    }

    @Test
    @Order(3)
    @Tag("getById_method")
    @DisplayName("Test find by id ClientServiceImpl")
    public void testFindById() {
        String uuid = "123455896";
        var clientModelMock = mock(ClientModel.class);

        when(clientRepository.findById(uuid)).thenReturn(Optional.of(clientModelMock));

        ClientModel result = clientService.getClientById(uuid);

        verify(clientRepository).findById(uuid);
        assertEquals(clientModelMock, result);
    }

    @Test
    @Order(4)
    @Tag("getById_method")
    @DisplayName("Test find by id ClientServiceImpl")
    public void testFindByEmail() {
        String email = "123@email.com";
        var clientModelMock = mock(ClientModel.class);

        when(clientRepository.findByEmail(email)).thenReturn(Optional.of(clientModelMock));

        ClientModel result = clientService.getClientByEmail(email);

        verify(clientRepository).findByEmail(email);
        assertEquals(clientModelMock, result);
    }

    @Test
    @Order(5)
    @Tag("getById_method")
    @DisplayName("Test find by id client not found ClientServiceImpl")
    public void testFindByIdNotFound() {

        String uuid = "12345666666";

        when(clientRepository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> clientService.getClientById(uuid));
    }

    @Test
    @Order(6)
    @Tag("send_method")
    @DisplayName("Test send client to sale ClientServiceImpl")
    public void testSendClient() {

        var saleDataDto = new SaleDataDto("uuid", "client name", "123@eamil.com" );

        clientService.sendClientToSale(saleDataDto);
        try {
            verify(queuePostRabbitClient).postClientToSale(saleDataDto);
        } catch (Exception e) {}
    }

    @Test
    @Order(7)
    @Tag("update_method")
    @DisplayName("Test update ClientService")
    public void testUpdate() {

        String uuid = "123456";
        var clientDto = mock(ClientDto.class);
        var existingClient = mock(ClientModel.class);

        when(clientRepository.findById(uuid)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(existingClient)).thenReturn(existingClient);

        ClientModel result = clientService.updateClient(uuid, clientDto);

        verify(clientRepository).findById(uuid);
        verify(clientRepository).save(existingClient);
        assertEquals(existingClient, result);
    }

    @Test
    @Order(8)
    @Tag("delete_method")
    @DisplayName("Test delete ClientServiceImpl")
    public void testDelete() {

        var clientModel = mock(ClientModel.class);
        String uuid = "123455896";

        when(clientRepository.findById(uuid)).thenReturn(Optional.of(clientModel));

        clientService.deleteClientById(uuid);

        verify(clientRepository).delete(clientModel);
    }

}