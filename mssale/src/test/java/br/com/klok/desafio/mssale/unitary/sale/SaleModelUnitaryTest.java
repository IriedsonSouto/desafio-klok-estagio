package br.com.klok.desafio.mssale.unitary.sale;

import br.com.klok.desafio.mssale.model.entity.SaleModel;
import br.com.klok.desafio.mssale.model.enums.SaleStatusEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SaleModelUnitaryTest {

    private static SaleModel saleModel;

    @BeforeAll
    @Test
    public static void setUp(){
        saleModel = new SaleModel();
    }

    @Test
    @Order(1)
    @DisplayName("Check Construct a Sale")
    public void checkUUIDCreate(){
        assertNotNull(saleModel.getUuid());
        assertNotNull(saleModel.getCreateDate());
    }

    @Test
    @Order(2)
    @DisplayName("Getters and setters")
    public void checkGetAndSet(){

        BigDecimal price = new BigDecimal(5);
        String clientId = "idteste";
        Date datePaid = new Date();

        saleModel.setPrice(price);
        saleModel.setStatus(SaleStatusEnum.PAID);
        saleModel.setClientId(clientId);
        saleModel.setPaidDate(datePaid);

        assertEquals(price, saleModel.getPrice());
        assertEquals(SaleStatusEnum.PAID, saleModel.getStatus());
        assertEquals(clientId, saleModel.getClientId());
        assertEquals(datePaid, saleModel.getPaidDate());
    }

}
