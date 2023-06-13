package br.com.klok.desafio.msclient.infra.mqconfig;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.client-sale}")
    private String queueSendClient;

    @Bean
    public Queue queueClientSale(){
        return new Queue(queueSendClient , true);
    }


}
