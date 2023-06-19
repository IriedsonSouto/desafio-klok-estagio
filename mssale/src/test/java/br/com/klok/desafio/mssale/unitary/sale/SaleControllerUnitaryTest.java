package br.com.klok.desafio.mssale.unitary.sale;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import br.com.klok.desafio.mssale.business.SaleService;
import br.com.klok.desafio.mssale.infra.data.ConsultProductDto;
import br.com.klok.desafio.mssale.model.entity.SaleModel;
import br.com.klok.desafio.mssale.presetation.controller.SaleController;
import br.com.klok.desafio.mssale.presetation.dto.SaleDto;
import br.com.klok.desafio.mssale.presetation.dto.SaleWithProductDto;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SaleControllerUnitaryTest {

    @Mock
    private SaleService saleService;

    @InjectMocks
    private SaleController saleController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    @Tag("create_method")
    @DisplayName("Test create SaleController")
    public void testCreateSale() {
        var saleDtoMock = mock(SaleDto.class);

        ResponseEntity<String> response = saleController.createSale(saleDtoMock);

        verify(saleService).createSale(saleDtoMock);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Successfully", response.getBody());
    }

    @Test
    @Order(2)
    @Tag("getAll_method")
    @DisplayName("Test find all SaleController")
    public void testGetAll() {
        List<SaleModel> sales = new ArrayList<>();

        when(saleService.getAllSale()).thenReturn(sales);

        ResponseEntity<List<SaleModel>> response = saleController.getAllSales();

        verify(saleService).getAllSale();
        Assertions. assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(sales, response.getBody());
    }

    @Test
    @Order(3)
    @Tag("getById_method")
    @DisplayName("Test find by id SaleController")
    public void testFindById() {
        String uuid = "abc123";
        var saleModelMock = mock(SaleModel.class);
        var saleProductMock = mock(SaleWithProductDto.class);

        when(saleService.getSaleById(uuid)).thenReturn(saleModelMock);
        when(saleService.getSaleWithProductById(uuid)).thenReturn(saleProductMock);

        ResponseEntity<SaleWithProductDto> response = saleController.getSaleById(uuid);

        verify(saleService).getSaleWithProductById(uuid);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(saleProductMock, response.getBody());
    }

    @Test
    @Order(4)
    @Tag("getById_method")
    @DisplayName("Test find by client id SaleController")
    public void testFindByClientId() {
        String uuid = "abc123";
        List<SaleModel> sales = new ArrayList<>();

        when(saleService.getSaleByClientId(uuid)).thenReturn(sales);
        ResponseEntity<List<SaleModel>> response = saleController.getSaleByClientId(uuid);

        verify(saleService).getSaleByClientId(uuid);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(sales, response.getBody());
    }

    @Test
    @Order(5)
    @Tag("update_method")
    @DisplayName("Test update productList SaleController")
    public void testUpdateProductSale() {
        String uuid = "abc123";
        var consultProduct = new ConsultProductDto("123", 2);
        var saleModelMock = mock(SaleModel.class);

        when(saleService.consultProductToUpdateList(uuid, consultProduct)).thenReturn(saleModelMock);

        ResponseEntity<SaleModel> response = saleController.updateSaleProductList(uuid, consultProduct);

        verify(saleService).consultProductToUpdateList(uuid, consultProduct);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(saleModelMock, response.getBody());
    }

    @Test
    @Order(6)
    @Tag("delete_method")
    @DisplayName("Test delete SaleController")
    public void testDeleteSale() {
        String uuid = "abc123";

        var saleModel = mock(SaleModel.class);
        when(saleModel.getUuid()).thenReturn(uuid);

        when(saleService.getSaleById(uuid)).thenReturn(saleModel);
        ResponseEntity<String> response = saleController.deleteSaleById(uuid);

        verify(saleService).deleteSaleById(uuid);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Successfully deleted!", response.getBody());
    }
}