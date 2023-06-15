package br.com.klok.desafio.msproduct.infra.mqconfig;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.product-sale}")
    private String queueSendProduct;

    @Bean
    public Queue queueProductSale(){
        return new Queue(queueSendProduct , true);
    }


}
