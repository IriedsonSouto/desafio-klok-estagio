package br.com.klok.desafio.mspayment.infra;

import br.com.klok.desafio.mspayment.presetation.dto.PaymentDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostRabbitClient {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queuePaymentSend;

    public void sendToSale(PaymentDto paymentDto) throws JsonProcessingException {

        var paymentDtoJson = convertToJson(paymentDto);
        rabbitTemplate.convertAndSend(queuePaymentSend.getName(), paymentDtoJson);
    }

    public String convertToJson(PaymentDto paymentDto) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(paymentDto);
    }

}
