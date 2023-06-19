package br.com.klok.desafio.mspayment.unitary.payment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.klok.desafio.mspayment.business.service.impl.PaymentServiceImpl;
import br.com.klok.desafio.mspayment.infra.PostRabbitClient;
import br.com.klok.desafio.mspayment.infra.data.SalePaymentData;
import br.com.klok.desafio.mspayment.model.entity.PaymentModel;
import br.com.klok.desafio.mspayment.model.repository.PaymentRepository;
import br.com.klok.desafio.mspayment.presetation.dto.PaymentDto;
import br.com.klok.desafio.mssale.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class PaymentServiceUnitaryTest {

    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private PostRabbitClient queuePostRabbitClient;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @Order(1)
    @Tag("save_method")
    @DisplayName("Test Save PaymentServiceImpl")
    public void testCreate() {

        var paymentDto = mock(PaymentDto.class);

        paymentService.createPayment(paymentDto);

        try {
            verify(queuePostRabbitClient).sendToSale(any(PaymentDto.class));
        } catch (Exception e) {}
    }

    @Test
    @Order(2)
    @Tag("create_method")
    @DisplayName("Test create PaymentServiceImpl")
    public void testSave() {

        var paymentDto = mock(PaymentDto.class);
        var paymentModel = mock(PaymentModel.class);

        when(paymentRepository.save(any(PaymentModel.class))).thenReturn(paymentModel);

        paymentService.savePayment(paymentDto);

        verify(paymentRepository).save(any(PaymentModel.class));
    }

    @Test
    @Order(3)
    @Tag("getAll_method")
    @DisplayName("Test find all PaymentServiceImpl")
    public void testGetAll() {

        List<PaymentModel> paymentList = new ArrayList<>();

        when(paymentRepository.findAll()).thenReturn(paymentList);

        List<PaymentModel> result = paymentService.getAllPayment();

        verify(paymentRepository).findAll();
        assertEquals(paymentList, result);
    }

    @Test
    @Order(4)
    @Tag("getById_method")
    @DisplayName("Test find by id PaymentServiceImpl")
    public void testFindById() {
        String uuid = "123455896";
        var paymentModelMock = mock(PaymentModel.class);

        when(paymentRepository.findById(uuid)).thenReturn(Optional.of(paymentModelMock));

        PaymentModel result = paymentService.getPaymentById(uuid);

        verify(paymentRepository).findById(uuid);
        assertEquals(paymentModelMock, result);
    }

    @Test
    @Order(5)
    @Tag("getById_method")
    @DisplayName("Test find by id payment not found PaymentServiceImpl")
    public void testFindByIdNotFound() {

        String uuid = "12345666666";

        when(paymentRepository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> paymentService.getPaymentById(uuid));
    }

    @Test
    @Order(6)
    @Tag("update_method")
    @DisplayName("Test update PaymentService")
    public void testUpdate() {

        String uuid = "123456";
        var paymentDto = mock(PaymentDto.class);
        var existingPayment = mock(PaymentModel.class);

        when(paymentRepository.findById(uuid)).thenReturn(Optional.of(existingPayment));
        when(paymentRepository.save(existingPayment)).thenReturn(existingPayment);

        PaymentModel result = paymentService.updatePaymentById(uuid, paymentDto);

        verify(paymentRepository).findById(uuid);
        verify(paymentRepository).save(existingPayment);
        assertEquals(existingPayment, result);
    }

    @Test
    @Order(7)
    @Tag("delete_method")
    @DisplayName("Test delete PaymentServiceImpl")
    public void testDelete() {

        var paymentModel = mock(PaymentModel.class);
        String uuid = "123455896";

        when(paymentRepository.findById(uuid)).thenReturn(Optional.of(paymentModel));

        paymentService.deletePaymentById(uuid);

        verify(paymentRepository).delete(paymentModel);
    }

}