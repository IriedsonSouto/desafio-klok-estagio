package br.com.klok.desafio.mspayment.unitary.payment;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import br.com.klok.desafio.mspayment.business.PaymentService;
import br.com.klok.desafio.mspayment.model.entity.PaymentModel;
import br.com.klok.desafio.mspayment.presetation.controller.PaymentController;
import br.com.klok.desafio.mspayment.presetation.dto.PaymentDto;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PaymentControllerUnitaryTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    @Tag("create_method")
    @DisplayName("Test create PaymentController")
    public void testCreateProduct() {
        var paymentDtoMock = mock(PaymentDto.class);

        ResponseEntity<String> response = paymentController.createPayment(paymentDtoMock);

        verify(paymentService).createPayment(paymentDtoMock);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Successfully", response.getBody());
    }

    @Test
    @Order(2)
    @Tag("getAll_method")
    @DisplayName("Test find all PaymentController")
    public void testGetAll() {
        List<PaymentModel> payments = new ArrayList<>();

        when(paymentService.getAllPayment()).thenReturn(payments);

        ResponseEntity<List<PaymentModel>> response = paymentController.getAllPayment();

        verify(paymentService).getAllPayment();
        Assertions. assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(payments, response.getBody());
    }

    @Test
    @Order(3)
    @Tag("getById_method")
    @DisplayName("Test find by id PaymentController")
    public void testFindById() {
        String uuid = "abc123";
        var paymentModelMock = mock(PaymentModel.class);

        when(paymentService.getPaymentById(uuid)).thenReturn(paymentModelMock);

        ResponseEntity<PaymentModel> response = paymentController.getPaymentById(uuid);

        verify(paymentService).getPaymentById(uuid);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(paymentModelMock, response.getBody());
    }

    @Test
    @Order(4)
    @Tag("update_method")
    @DisplayName("Test update PaymentController")
    public void testUpdatePayment() {
        String uuid = "abc123";
        var paymentDtoMock = mock(PaymentDto.class);
        var updatedPaymentMock = mock(PaymentModel.class);

        when(paymentService.updatePaymentById(uuid, paymentDtoMock)).thenReturn(updatedPaymentMock);

        ResponseEntity<PaymentModel> response = paymentController.updatePaymentById(uuid, paymentDtoMock);

        verify(paymentService).updatePaymentById(uuid, paymentDtoMock);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(updatedPaymentMock, response.getBody());
    }

    @Test
    @Order(5)
    @Tag("delete_method")
    @DisplayName("Test delete PaymentController")
    public void testDeletePayment() {
        String uuid = "abc123";

        var paymentModel = mock(PaymentModel.class);
        when(paymentModel.getUuid()).thenReturn(uuid);

        when(paymentService.getPaymentById(uuid)).thenReturn(paymentModel);
        ResponseEntity<String> response = paymentController.deletePaymentById(uuid);

        verify(paymentService).deletePaymentById(uuid);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Successfully deleted!", response.getBody());
    }
}