package br.com.klok.desafio.mssale.infra;


import br.com.klok.desafio.mssale.business.SaleService;
import br.com.klok.desafio.mssale.infra.data.ClientDataDto;
import br.com.klok.desafio.mssale.infra.data.PaymentDataDto;
import br.com.klok.desafio.mssale.infra.data.ProductDataDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumerRabbitClient {

    private final SaleService saleService;

    @RabbitListener(queues = "${mq.queues.client-sale}")
    public void receiveClient(@Payload String payload){
        try {
            var clientData = new ObjectMapper().readValue(payload, ClientDataDto.class);

            var saleModel = this.saleService.saveSale(clientData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @RabbitListener(queues = "${mq.queues.product-sale}")
    public void receiveProduct(@Payload String payload){
        try {
            var productData = new ObjectMapper().readValue(payload, ProductDataDto.class);

            this.saleService.updateSaleProductList(productData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @RabbitListener(queues = "${mq.queues.payment-sale}")
    public void receivePayment(@Payload String payload){
        try {
            var paymentData = new ObjectMapper().readValue(payload, PaymentDataDto.class);

            this.saleService.responseSaleToPayment(paymentData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
