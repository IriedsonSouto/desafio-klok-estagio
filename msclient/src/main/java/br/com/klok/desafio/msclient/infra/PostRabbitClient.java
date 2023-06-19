package br.com.klok.desafio.msclient.infra;

import br.com.klok.desafio.msclient.infra.data.SaleDataDto;
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
    private final Queue queueSendClient;

    public void postClientToSale(SaleDataDto saleDataDto) throws JsonProcessingException {

        var clientJson = convertToJson(saleDataDto);
        rabbitTemplate.convertAndSend(queueSendClient.getName(), clientJson);
    }

    public String convertToJson(SaleDataDto saleDataDto) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(saleDataDto);
    }

}
