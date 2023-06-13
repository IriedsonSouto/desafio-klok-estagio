package br.com.klok.desafio.msclient.infra;

import br.com.klok.desafio.msclient.business.service.ClientService;
import br.com.klok.desafio.msclient.infra.data.SaleData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetSale {

    private final ClientService clientService;

    @RabbitListener(queues = "${mq.queues.sale-client}")
    public void getSale(@Payload String payload) {
        try {
            var saleData = new ObjectMapper().readValue(payload, SaleData.class);

            var clientModel = this.clientService.getClientByEmail(saleData.getEmailClient());
            clientService.sendClient(clientModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

