package br.com.klok.desafio.msproduct.unitary.product;

import br.com.klok.desafio.msproduct.model.entity.ProductModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductModelUnitaryTest {

    private static ProductModel productModel;

    @BeforeAll
    @Test
    public static void setUp(){
        productModel = new ProductModel();
    }

    @Test
    @Order(1)
    @DisplayName("Check UUID Create a Product")
    public void checkUUIDCreate(){
        assertNotNull(productModel.getUuid());
    }

    @Test
    @Order(2)
    @DisplayName("Getters and setters")
    public void checkGetAndSet(){

        productModel.setName("Banana");
        productModel.setDescription("Para comer");
        productModel.setPrice(new BigDecimal(5));


        assertEquals("Banana", productModel.getName());
        assertEquals("Para comer", productModel.getDescription());
        assertEquals(new BigDecimal(5), productModel.getPrice());

    }

}
