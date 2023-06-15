package br.com.klok.desafio.mspayment.business.service.impl;

import br.com.klok.desafio.mspayment.business.PaymentService;
import br.com.klok.desafio.mspayment.infra.PostRabbitClient;
import br.com.klok.desafio.mspayment.model.entity.PaymentModel;
import br.com.klok.desafio.mspayment.presetation.dto.PaymentDto;
import br.com.klok.desafio.mspayment.model.repository.PaymentRepository;
import br.com.klok.desafio.mssale.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PostRabbitClient postRabbitClient;

    @Override
    public void createPayment(PaymentDto paymentDto) {
        try {
            postRabbitClient.sendToSale(paymentDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void savePayment(PaymentDto paymentDto) {
        var paymentModel = PaymentDto.convertToModel(paymentDto);
        paymentRepository.save(paymentModel);
    }


    @Override
    public List<PaymentModel> getAllPayment() {
        return paymentRepository.findAll();
    }

    @Override
    public PaymentModel getPaymentById(String uuid) {

        var optionalPayment = paymentRepository.findById(uuid);

        return optionalPayment.orElseThrow( () -> new EntityNotFoundException("Sale: " + uuid + " not found"));
    }

    @Override
    public PaymentModel updatePaymentById(String id, PaymentDto paymentDto) {
        return null;
    }

    @Override
    public void deletePaymentById(String uuid) {
        var payment = this.getPaymentById(uuid);
        paymentRepository.delete(payment);
    }

}
