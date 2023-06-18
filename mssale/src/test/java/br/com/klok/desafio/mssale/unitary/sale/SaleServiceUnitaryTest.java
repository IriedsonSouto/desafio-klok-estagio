package br.com.klok.desafio.mssale.unitary.sale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.klok.desafio.mssale.business.service.impl.SaleServiceImpl;
import br.com.klok.desafio.mssale.exception.EntityNotFoundException;
import br.com.klok.desafio.mssale.infra.PostRabbitClient;
import br.com.klok.desafio.mssale.infra.data.ClientDataDto;
import br.com.klok.desafio.mssale.model.entity.SaleModel;
import br.com.klok.desafio.mssale.model.repository.SaleRepository;
import br.com.klok.desafio.mssale.presetation.dto.SaleDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class SaleServiceUnitaryTest {

    @Mock
    private SaleRepository saleRepository;
    @Mock
    private PostRabbitClient queuePostRabbitClient;

    @InjectMocks
    private SaleServiceImpl saleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @Order(1)
    @Tag("save_method")
    @DisplayName("Test create SaleServiceImpl")
    public void testCreate() {

        var saleDto = mock(SaleDto.class);

        saleService.createSale(saleDto);

        try {
            verify(queuePostRabbitClient).postSaleToClient(any(SaleDto.class));
        } catch (Exception e) {}
    }

    @Test
    @Order(2)
    @Tag("create_method")
    @DisplayName("Test save SaleServiceImpl")
    public void testSave() {

        var clientDataDto = mock(ClientDataDto.class);
        var saleModel = mock(SaleModel.class);

        when(saleRepository.save(any(SaleModel.class))).thenReturn(saleModel);

        saleService.saveSale(clientDataDto);

        verify(saleRepository).save(any(SaleModel.class));
    }

    @Test
    @Order(3)
    @Tag("getAll_method")
    @DisplayName("Test find all SaleServiceImpl")
    public void testGetAll() {

        List<SaleModel> saleList = new ArrayList<>();

        when(saleRepository.findAll()).thenReturn(saleList);

        List<SaleModel> result = saleService.getAllSale();

        verify(saleRepository).findAll();
        assertEquals(saleList, result);
    }

    @Test
    @Order(4)
    @Tag("getById_method")
    @DisplayName("Test find by id SaleServiceImpl")
    public void testFindById() {
        String uuid = "123455896";
        var saleModelMock = mock(SaleModel.class);

        when(saleRepository.findById(uuid)).thenReturn(Optional.of(saleModelMock));

        SaleModel result = saleService.getSaleById(uuid);

        verify(saleRepository).findById(uuid);
        assertEquals(saleModelMock, result);
    }

    @Test
    @Order(5)
    @Tag("getById_method")
    @DisplayName("Test find by id sale not found SaleServiceImpl")
    public void testFindByIdNotFound() {

        String uuid = "12345666666";

        when(saleRepository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> saleService.getSaleById(uuid));
    }

    @Test
    @Order(6)
    @Tag("update_method")
    @DisplayName("Test update SaleService")
    public void testUpdate() {

    }

    @Test
    @Order(7)
    @Tag("delete_method")
    @DisplayName("Test delete SaleServiceImpl")
    public void testDelete() {

        var saleModel = mock(SaleModel.class);
        String uuid = "123455896";

        when(saleRepository.findById(uuid)).thenReturn(Optional.of(saleModel));

        saleService.deleteSaleById(uuid);

        verify(saleRepository).delete(saleModel);
    }

}