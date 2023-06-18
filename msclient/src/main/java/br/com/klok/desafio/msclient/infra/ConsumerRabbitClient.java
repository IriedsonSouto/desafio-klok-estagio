package br.com.klok.desafio.msclient.infra;

import br.com.klok.desafio.msclient.business.service.ClientService;
import br.com.klok.desafio.msclient.infra.data.SaleDataDto;
import br.com.klok.desafio.msclient.infra.data.SalesChargeDataDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsumerRabbitClient {

    private final ClientService clientService;

    @RabbitListener(queues = "${mq.queues.sale-client}")
    public void receiveSale(@Payload String payload) {
        try {
            var saleDataDtoReceive = new ObjectMapper().readValue(payload, SaleDataDto.class);

            var clientModel = this.clientService.getClientByEmail(saleDataDtoReceive.email());
            var saleDataDtoSend = new SaleDataDto(clientModel.getUuid(), clientModel.getName(), clientModel.getEmail());

            clientService.sendClientToSale(saleDataDtoSend);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "${mq.queues.saleToClient-demand}")
    public void receiveSalesCharge(@Payload String payload) {
        try {
            var saleChargeData = new ObjectMapper().readValue(payload, new TypeReference<List<SalesChargeDataDto>>(){});

            for (SalesChargeDataDto saleDataDto : saleChargeData) {
                var clientModel = this.clientService.getClientById(saleDataDto.clientId());
                //method for building the message using sales and customer data
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

