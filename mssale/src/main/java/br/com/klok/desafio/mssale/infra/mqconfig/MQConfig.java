package br.com.klok.desafio.mssale.infra.mqconfig;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.sale-client}")
    private String queueSendSale;

    @Bean
    public Queue queueSaleClient(){
        return new Queue(queueSendSale , true);
    }


}
