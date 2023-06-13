package br.com.klok.desafio.mssale.infra;

import br.com.klok.desafio.mssale.model.entity.SaleModel;
import br.com.klok.desafio.mssale.presetation.dto.SaleDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendSale {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueSendClient;

    public void getSaleToSend(SaleDto saleDto) throws JsonProcessingException {

        var saleDtoJson = convertToJson(saleDto);
        rabbitTemplate.convertAndSend(queueSendClient.getName(), saleDtoJson);
    }

    public String convertToJson(SaleDto saleDto) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(saleDto);
    }

}
