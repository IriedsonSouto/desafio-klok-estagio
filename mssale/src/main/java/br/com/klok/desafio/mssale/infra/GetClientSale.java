package br.com.klok.desafio.mssale.infra;


import br.com.klok.desafio.mssale.business.SaleService;
import br.com.klok.desafio.mssale.infra.data.ClientData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetClientSale {

    private final SaleService saleService;

    @RabbitListener(queues = "${mq.queues.client-sale}")
    public void reciveClient(@Payload String payload){
        try {
            var clientData = new ObjectMapper().readValue(payload, ClientData.class);

            var saleModel = this.saleService.saveSale(clientData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
