package br.com.klok.desafio.mssale.unitary.sale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.klok.desafio.mssale.business.service.impl.SaleServiceImpl;
import br.com.klok.desafio.mssale.exception.EntityNotFoundException;
import br.com.klok.desafio.mssale.infra.PostRabbitClient;
import br.com.klok.desafio.mssale.infra.data.ClientDataDto;
import br.com.klok.desafio.mssale.infra.data.ConsultProductDto;
import br.com.klok.desafio.mssale.infra.data.PaymentDataDto;
import br.com.klok.desafio.mssale.infra.data.ProductDataDto;
import br.com.klok.desafio.mssale.model.entity.SaleModel;
import br.com.klok.desafio.mssale.model.entity.SaleProductModel;
import br.com.klok.desafio.mssale.model.enums.SaleStatusEnum;
import br.com.klok.desafio.mssale.model.repository.SaleProductRepository;
import br.com.klok.desafio.mssale.model.repository.SaleRepository;
import br.com.klok.desafio.mssale.presetation.dto.SaleDto;
import br.com.klok.desafio.mssale.presetation.dto.SaleWithProductDto;
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
    private SaleProductRepository saleProductRepository;
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

        var clientDataDto = new ClientDataDto("uuid", "name", "email@exemple.com");
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
    @DisplayName("Test find sale with product by id SaleServiceImpl")
    public void testFindSaleWithProductById() {
        String uuid = "123455896";
        var saleModelMock = mock(SaleModel.class);
        var saleProductList = new ArrayList<SaleProductModel>();

        when(saleRepository.findById(uuid)).thenReturn(Optional.of(saleModelMock));
        when(saleProductRepository.getSaleProductsBySaleId(uuid)).thenReturn(saleProductList);
        when(saleModelMock.getUuid()).thenReturn(uuid);
        when(saleModelMock.getStatus()).thenReturn(SaleStatusEnum.CREATED);
        when(saleModelMock.getPrice()).thenReturn(new BigDecimal(5));
        when(saleModelMock.getCreateDate()).thenReturn(new Date());
        when(saleModelMock.getPaidDate()).thenReturn(new Date());

        SaleWithProductDto result = saleService.getSaleWithProductById(uuid);

        verify(saleProductRepository).getSaleProductsBySaleId(uuid);
        verify(saleRepository).findById(uuid);
        assertNotNull(result);
    }

    @Test
    @Order(6)
    @Tag("getByClientId_method")
    @DisplayName("Test find by client id SaleServiceImpl")
    public void testFindClientById() {
        String uuid = "123455896";
        var saleModelMock = new ArrayList<SaleModel>();

        when(saleRepository.findByClientId(uuid)).thenReturn(saleModelMock);

        List<SaleModel> result = saleService.getSaleByClientId(uuid);

        verify(saleRepository).findByClientId(uuid);
        assertEquals(saleModelMock, result);
    }

    @Test
    @Order(7)
    @Tag("getById_method")
    @DisplayName("Test find by id sale not found SaleServiceImpl")
    public void testFindByIdNotFound() {

        String uuid = "12345666666";

        when(saleRepository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> saleService.getSaleById(uuid));
    }

    @Test
    @Order(8)
    @Tag("send_method")
    @DisplayName("Test send sale to payment SaleService")
    public void testSaleToPayment() {

        String uuid = "123455896";
        var saleModelMock = mock(SaleModel.class);
        var paymentSale = new PaymentDataDto("123455896", "PIX");

        when(saleRepository.findById(uuid)).thenReturn(Optional.of(saleModelMock));

        saleService.responseSaleToPayment(paymentSale);

        try {
            verify(queuePostRabbitClient).postSaleToPayment(paymentSale);
        } catch(Exception e){}

    }

    @Test
    @Order(9)
    @Tag("consult_method")
    @DisplayName("Test consult product SaleService")
    public void testConsultProduct() {

        String uuid = "123455896";
        var saleModelMock = mock(SaleModel.class);
        var consultProduct = new ConsultProductDto("123", 2);

        when(saleRepository.findById(uuid)).thenReturn(Optional.of(saleModelMock));
        when(saleModelMock.getUuid()).thenReturn("123455896");

        saleService.consultProductToUpdateList(uuid, consultProduct);

        try {
            verify(queuePostRabbitClient).postSaleToProduct(any(ProductDataDto.class));
        } catch(Exception e){}

    }

    @Test
    @Order(10)
    @Tag("update_method")
    @DisplayName("Test update add product list sale SaleServiceImpl")
    public void testUpdateAddSaleProductList() {

        String uuid = "123455896";
        var productData = new ProductDataDto("12345"
                                           , "banana"
                                           , "3212"
                                           , new BigDecimal(5)
                                           , 2);
        var saleModelMock = mock(SaleModel.class);
        var product = new ArrayList<SaleProductModel>();


        when(saleRepository.findById(productData.saleUuid())).thenReturn(Optional.of(saleModelMock));
        when(saleProductRepository.getSaleProductsBySaleId(saleModelMock.getUuid())).thenReturn(product);

        SaleWithProductDto saleWithProduct = saleService.updateSaleProductList(productData);

        verify(saleProductRepository).save(any(SaleProductModel.class));
        verify(saleRepository).save(saleModelMock);
        assertNotNull(saleWithProduct.getProducts());
    }

    @Test
    @Order(11)
    @Tag("update_method")
    @DisplayName("Test update delete product list sale SaleServiceImpl")
    public void testUpdateDeleteSaleProductList() {

        String uuid = "123455896";
        var productData = new ProductDataDto("12345"
                                           , "banana"
                                           , "3212"
                                           , new BigDecimal(5)
                                           , 2);
        var saleModelMock = mock(SaleModel.class);
        var saleProduct = new SaleProductModel();
        var product = new ArrayList<SaleProductModel>();
        product.add(saleProduct);

        saleProduct.setUuid("3212");
        saleProduct.setName("banana");
        saleProduct.setSaleModel(saleModelMock);
        saleProduct.setQuantity(2);
        saleProduct.setPrice(new BigDecimal(5));
        when(saleRepository.findById(productData.saleUuid())).thenReturn(Optional.of(saleModelMock));
        when(saleProductRepository.getSaleProductsBySaleId(saleModelMock.getUuid())).thenReturn(product);

        SaleWithProductDto saleWithProduct = saleService.updateSaleProductList(productData);


        verify(saleProductRepository).delete(any(SaleProductModel.class));
        verify(saleRepository).save(saleModelMock);
        assertNotNull(saleWithProduct.getProducts());
    }

    @Test
    @Order(12)
    @Tag("update_method")
    @DisplayName("Test update sale status SaleServiceImpl")
    public void testUpdateSaleStatus() {

        var saleModelMock = mock(SaleModel.class);

        saleService.updateSaleStatus(saleModelMock);

        verify(saleRepository).save(saleModelMock);
    }

    @Test
    @Order(13)
    @Tag("delete_method")
    @DisplayName("Test delete SaleServiceImpl")
    public void testDelete() {

        String uuid = "123455896";
        var saleModelMock = mock(SaleModel.class);
        var saleProductList = new ArrayList<SaleProductModel>();

        when(saleProductRepository.getSaleProductsBySaleId(uuid)).thenReturn(saleProductList);
        when(saleRepository.findById(uuid)).thenReturn(Optional.of(saleModelMock));
        when(saleModelMock.getStatus()).thenReturn(SaleStatusEnum.CREATED);

        saleService.deleteSaleById(uuid);

        verify(saleProductRepository).deleteAll(saleProductList);
        verify(saleRepository).delete(saleModelMock);
    }

    @Test
    @Order(14)
    @Tag("send_method")
    @DisplayName("Test send Sales Charge SaleServiceImpl")
    public void testSalesCharge() {

        List<SaleModel> saleList = new ArrayList<>();

        when(saleRepository.findAll()).thenReturn(saleList);

        saleService.salesCharge();

        verify(queuePostRabbitClient).postSalesCharge(saleList);
    }

}