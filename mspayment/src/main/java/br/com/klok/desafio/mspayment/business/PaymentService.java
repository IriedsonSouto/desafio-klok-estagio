package br.com.klok.desafio.mspayment.business;

import br.com.klok.desafio.mspayment.model.entity.PaymentModel;
import br.com.klok.desafio.mspayment.presetation.dto.PaymentDto;

import java.util.List;

public interface PaymentService {


    public void createPayment(PaymentDto sale);

    public List<PaymentModel> getAllPayment();

    public PaymentModel getPaymentById(String id);

    public PaymentModel updatePaymentById(String id, PaymentDto paymentDto);

    public void deletePaymentById(String id);

}
