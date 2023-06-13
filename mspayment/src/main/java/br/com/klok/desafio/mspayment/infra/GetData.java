package br.com.klok.desafio.mspayment.infra;


import br.com.klok.desafio.mspayment.business.PaymentService;
import br.com.klok.desafio.mssale.infra.data.SaleData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetData {

    private final PaymentService saleService;

    @RabbitListener(queues = "${mq.queues.sale-payment}")
    public void reciveSale(@Payload String payload){
        try {
            var clientData = new ObjectMapper().readValue(payload, SaleData.class);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
