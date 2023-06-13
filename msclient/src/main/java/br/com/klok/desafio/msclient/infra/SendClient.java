package br.com.klok.desafio.msclient.infra;

import br.com.klok.desafio.msclient.model.entity.ClientModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendClient {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueSendClient;

    public void getClientToSend(ClientModel clientModel) throws JsonProcessingException {

        var clientJson = convertToJson(clientModel);
        rabbitTemplate.convertAndSend(queueSendClient.getName(), clientJson);
    }

    public String convertToJson(ClientModel clientModel) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(clientModel);
    }

}
