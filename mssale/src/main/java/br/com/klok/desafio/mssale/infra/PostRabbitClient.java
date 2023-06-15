package br.com.klok.desafio.mssale.infra;

import br.com.klok.desafio.mssale.infra.data.PaymentDataDto;
import br.com.klok.desafio.mssale.infra.data.ProductDataDto;
import br.com.klok.desafio.mssale.presetation.dto.SaleDto;
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
    private final Queue queueSaleClient;
    private final Queue queueSalePayment;
    private final Queue queueSaleProduct;

    public void postSaleToClient(SaleDto saleDto) throws JsonProcessingException {

        var saleDtoJson = convertToJson(saleDto);
        rabbitTemplate.convertAndSend(queueSaleClient.getName(), saleDtoJson);
    }

    public void postSaleToPayment(PaymentDataDto paymentDataDto) throws JsonProcessingException {

        var saleDtoJson = convertToJson(paymentDataDto);
        rabbitTemplate.convertAndSend(queueSalePayment.getName(), saleDtoJson);
    }

    public void postSaleToProduct(ProductDataDto productDataDto) throws JsonProcessingException {

        var saleDtoJson = convertToJson(productDataDto);
        rabbitTemplate.convertAndSend(queueSaleProduct.getName(), saleDtoJson);
    }

    public String convertToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

}
