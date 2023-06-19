package br.com.klok.desafio.msproduct.unitary.product;

import br.com.klok.desafio.msproduct.model.entity.ProductModel;
import br.com.klok.desafio.msproduct.presetation.dto.ProductDto;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductDtoUnitaryTest {

    private static ProductDto productDto;
    private static ProductModel productModelMock;


    @BeforeAll
    public static void setup() {
        productDto = new ProductDto();
        productModelMock = mock(ProductModel.class);
    }

    @Test
    @Order(1)
    @Tag("sets_gets_method")
    @DisplayName("Setters and Getters method ProductDTO")
    public void testProductDTOSetterAndGetter() {

        productDto.setName("Banana");
        productDto.setDescription("Para comer");
        productDto.setPrice(new BigDecimal(5));

        assertEquals("Banana", productDto.getName());
        assertEquals("Para comer", productDto.getDescription());
        assertEquals(new BigDecimal(5), productDto.getPrice());
    }

    @Test
    @Order(2)
    @Tag("construct_method")
    @DisplayName("Constructor method ProductDTO by ProductModel")
    public void testProductDTOByProductModel() {

        when(productModelMock.getName()).thenReturn("Banana");
        when(productModelMock.getDescription()).thenReturn("Para comer");
        when(productModelMock.getPrice()).thenReturn(new BigDecimal(5));

        productDto = new ProductDto(productModelMock);

        assertEquals("Banana", productDto.getName());
        assertEquals("Para comer", productDto.getDescription());
        assertEquals(new BigDecimal(5), productDto.getPrice());
    }

    @Test
    @Order(3)
    @Tag("convert_method")
    @DisplayName("Convert ProductDTO for ProductModel")
    public void testProductDTOForProductModel() {

        ProductModel productModel = ProductDto.convertToModel(productDto);
        assertInstanceOf(ProductModel.class, productModel);
    }

}


