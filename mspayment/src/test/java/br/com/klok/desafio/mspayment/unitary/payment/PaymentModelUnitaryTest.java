package br.com.klok.desafio.mspayment.unitary.payment;

import br.com.klok.desafio.mspayment.model.entity.PaymentModel;
import br.com.klok.desafio.mspayment.model.enums.PaymentMethodEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaymentModelUnitaryTest {

    private static PaymentModel paymentModel;

    @BeforeAll
    @Test
    public static void setUp(){
        paymentModel = new PaymentModel();
    }

    @Test
    @Order(1)
    @DisplayName("Check Construct a Payment")
    public void checkUUIDCreate(){
        assertNotNull(paymentModel.getUuid());
        assertNotNull(paymentModel.getCreateDate());
    }

    @Test
    @Order(2)
    @DisplayName("Getters and setters")
    public void checkGetAndSet(){

        paymentModel.setMethod(PaymentMethodEnum.CASH);
        paymentModel.setSaleId("testeuuid");

        assertEquals(PaymentMethodEnum.CASH, paymentModel.getMethod());
        assertEquals("testeuuid", paymentModel.getSaleId());
    }

}
