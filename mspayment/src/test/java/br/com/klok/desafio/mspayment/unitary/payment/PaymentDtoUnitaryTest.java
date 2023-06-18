package br.com.klok.desafio.mspayment.unitary.payment;

import br.com.klok.desafio.mspayment.model.entity.PaymentModel;
import br.com.klok.desafio.mspayment.model.enums.PaymentMethodEnum;
import br.com.klok.desafio.mspayment.presetation.dto.PaymentDto;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaymentDtoUnitaryTest {

    private static PaymentDto paymentDto;
    private static PaymentModel paymentModelMock;


    @BeforeAll
    public static void setup() {
        paymentDto = new PaymentDto();
        paymentModelMock = mock(PaymentModel.class);
    }

    @Test
    @Order(1)
    @Tag("sets_gets_method")
    @DisplayName("Setters and Getters method PaymentDTO")
    public void testPaymentDTOSetterAndGetter() {

        paymentDto.setMethod(PaymentMethodEnum.PIX);
        paymentDto.setUuidSale("testeuuid");

        assertEquals(PaymentMethodEnum.PIX, paymentDto.getMethod());
        assertEquals("testeuuid", paymentDto.getUuidSale());
    }

    @Test
    @Order(2)
    @Tag("construct_method")
    @DisplayName("Constructor method PaymentDTO by PaymentModel")
    public void testPaymentDTOByPaymentModel() {

        when(paymentModelMock.getMethod()).thenReturn(PaymentMethodEnum.PIX);
        when(paymentModelMock.getSaleId()).thenReturn("testeuuid");

        paymentDto = new PaymentDto(paymentModelMock);

        assertEquals(PaymentMethodEnum.PIX, paymentDto.getMethod());
        assertEquals("testeuuid", paymentDto.getUuidSale());
    }

    @Test
    @Order(3)
    @Tag("convert_method")
    @DisplayName("Convert PaymentDTO for PaymentModel")
    public void testPaymentDTOForPaymentModel() {

        PaymentModel paymentModel = PaymentDto.convertToModel(paymentDto);
        assertInstanceOf(PaymentModel.class, paymentModel);
        assertNotNull(paymentModel.getUuid());
        assertNotNull(paymentModel.getCreateDate());
    }

}


