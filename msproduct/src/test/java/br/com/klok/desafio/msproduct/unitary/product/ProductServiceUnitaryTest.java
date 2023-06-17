package br.com.klok.desafio.msproduct.unitary.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.klok.desafio.msproduct.business.service.impl.ProductServiceImpl;
import br.com.klok.desafio.msproduct.exception.EntityNotFoundException;
import br.com.klok.desafio.msproduct.infra.PostRabbitClient;
import br.com.klok.desafio.msproduct.infra.data.ProductSaleDataDto;
import br.com.klok.desafio.msproduct.model.entity.ProductModel;
import br.com.klok.desafio.msproduct.model.repository.ProductRepository;
import br.com.klok.desafio.msproduct.presetation.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class ProductServiceUnitaryTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private PostRabbitClient queuePostRabbitClient;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @Order(1)
    @Tag("save_method")
    @DisplayName("Test Save ProductServiceImpl")
    public void testSave() {

        var productDto = mock(ProductDto.class);
        var productModel = mock(ProductModel.class);

        when(productRepository.save(any(ProductModel.class))).thenReturn(productModel);

        ProductModel result = productService.saveProduct(productDto);

        verify(productRepository).save(any(ProductModel.class));
        assertEquals(productModel, result);
    }

    @Test
    @Order(2)
    @Tag("getAll_method")
    @DisplayName("Test find all ProductServiceImpl")
    public void testGetAll() {

        List<ProductModel> productList = new ArrayList<>();

        when(productRepository.findAll()).thenReturn(productList);

        List<ProductModel> result = productService.getAllProduct();

        verify(productRepository).findAll();
        assertEquals(productList, result);
    }

    @Test
    @Order(3)
    @Tag("getById_method")
    @DisplayName("Test find by id ProductServiceImpl")
    public void testFindById() {
        String uuid = "123455896";
        var productModelMock = mock(ProductModel.class);

        when(productRepository.findById(uuid)).thenReturn(Optional.of(productModelMock));

        ProductModel result = productService.getProductById(uuid);

        verify(productRepository).findById(uuid);
        assertEquals(productModelMock, result);
    }

    @Test
    @Order(4)
    @Tag("getById_method")
    @DisplayName("Test find by id product not found ProductServiceImpl")
    public void testFindByIdNotFound() {

        String uuid = "12345666666";

        when(productRepository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> productService.getProductById(uuid));
    }

    @Test
    @Order(5)
    @Tag("send_method")
    @DisplayName("Test send Product to sale ProductServiceImpl")
    public void testSendProduct() {

        var productSaleDataDto = new ProductSaleDataDto("uuid"
                                                      , "Banana"
                                                      , "1234"
                                                      , new BigDecimal(5)
                                                      , 2);

        productService.sendProductToSale(productSaleDataDto);
        try {
            verify(queuePostRabbitClient).postProductToSale(productSaleDataDto);
        } catch (Exception e) {}
    }

    @Test
    @Order(6)
    @Tag("update_method")
    @DisplayName("Test update ProductService")
    public void testUpdate() {

        String uuid = "123456";
        var productDto = mock(ProductDto.class);
        var existingProduct = mock(ProductModel.class);

        when(productRepository.findById(uuid)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        ProductModel result = productService.updateProduct(uuid, productDto);

        verify(productRepository).findById(uuid);
        verify(productRepository).save(existingProduct);
        assertEquals(existingProduct, result);
    }

    @Test
    @Order(7)
    @Tag("delete_method")
    @DisplayName("Test delete ProductServiceImpl")
    public void testDelete() {

        var productModel = mock(ProductModel.class);
        String uuid = "123455896";

        when(productRepository.findById(uuid)).thenReturn(Optional.of(productModel));

        productService.deleteProductById(uuid);

        verify(productRepository).delete(productModel);
    }

}