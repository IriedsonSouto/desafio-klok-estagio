package br.com.klok.desafio.msproduct.unitary.product;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import br.com.klok.desafio.msproduct.business.service.ProductService;
import br.com.klok.desafio.msproduct.model.entity.ProductModel;
import br.com.klok.desafio.msproduct.presetation.controller.ProductController;
import br.com.klok.desafio.msproduct.presetation.dto.ProductDto;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProductControllerUnitaryTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    @Tag("create_method")
    @DisplayName("Test create ProductController")
    public void testCreateProduct() {
        var productDtoMock = mock(ProductDto.class);
        var productModelMock = mock(ProductModel.class);

        when(productService.saveProduct(productDtoMock)).thenReturn(productModelMock);

        ResponseEntity<ProductModel> response = productController.createProduct(productDtoMock);

        verify(productService).saveProduct(productDtoMock);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(productModelMock, response.getBody());
    }

    @Test
    @Order(2)
    @Tag("getAll_method")
    @DisplayName("Test find all ProductController")
    public void testGetAll() {
        List<ProductModel> products = new ArrayList<>();

        when(productService.getAllProduct()).thenReturn(products);

        ResponseEntity<List<ProductModel>> response = productController.getAllProducts();

        verify(productService).getAllProduct();
        Assertions. assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(products, response.getBody());
    }

    @Test
    @Order(3)
    @Tag("getById_method")
    @DisplayName("Test find by id ProductController")
    public void testFindById() {
        String uuid = "abc123";
        var productModelMock = mock(ProductModel.class);

        when(productService.getProductById(uuid)).thenReturn(productModelMock);

        ResponseEntity<ProductModel> response = productController.getProductById(uuid);

        verify(productService).getProductById(uuid);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(productModelMock, response.getBody());
    }

    @Test
    @Order(4)
    @Tag("update_method")
    @DisplayName("Test update ProductController")
    public void testUpdateProduct() {
        String uuid = "abc123";
        var productDtoMock = mock(ProductDto.class);
        var updatedProductMock = mock(ProductModel.class);

        when(productService.updateProduct(uuid, productDtoMock)).thenReturn(updatedProductMock);

        ResponseEntity<ProductModel> response = productController.updateProductById(uuid, productDtoMock);

        verify(productService).updateProduct(uuid, productDtoMock);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(updatedProductMock, response.getBody());
    }

    @Test
    @Order(5)
    @Tag("delete_method")
    @DisplayName("Test delete ProductController")
    public void testDeleteProduct() {
        String uuid = "abc123";

        var productModel = mock(ProductModel.class);
        when(productModel.getUuid()).thenReturn(uuid);

        when(productService.getProductById(uuid)).thenReturn(productModel);
        ResponseEntity<String> response = productController.deleteProductById(uuid);

        verify(productService).deleteProductById(uuid);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Successfully deleted!", response.getBody());
    }
}