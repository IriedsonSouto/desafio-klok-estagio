package br.com.klok.desafio.msproduct.infra;

import br.com.klok.desafio.msproduct.infra.data.ProductSaleDataDto;
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
    private final Queue queueSendProduct;

    public void postProductToSale(ProductSaleDataDto productSaleDataDto) throws JsonProcessingException {

        var productJson = convertToJson(productSaleDataDto);
        rabbitTemplate.convertAndSend(queueSendProduct.getName(), productJson);
    }

    public String convertToJson(ProductSaleDataDto productSaleDataDto) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(productSaleDataDto);
    }

}
