package br.com.klok.desafio.msproduct.infra;

import br.com.klok.desafio.msproduct.business.service.ProductService;
import br.com.klok.desafio.msproduct.infra.data.ProductSaleDataDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumerRabbitClient {

    private final ProductService productService;

    @RabbitListener(queues = "${mq.queues.sale-product}")
    public void receiveSale(@Payload String payload) {
        try {
            var productSaleDataDtoReceive = new ObjectMapper().readValue(payload, ProductSaleDataDto.class);

            var productModel = this.productService.getProductById(productSaleDataDtoReceive.productUuid());

            var productSaleDataDtoSend = new ProductSaleDataDto(productSaleDataDtoReceive.saleUuid()
                                                              , productModel.getName()
                                                              , productModel.getUuid()
                                                              , productModel.getPrice()
                                                              , productSaleDataDtoReceive.quantity());

            productService.sendProductToSale(productSaleDataDtoSend);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

