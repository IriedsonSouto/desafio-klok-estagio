package br.com.klok.desafio.mssale.infra.mqconfig;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.sale-client}")
    private String queueSendSaleToClient;

    @Value("${mq.queues.sale-payment}")
    private String queueSendSaleToPayment;

    @Value("${mq.queues.sale-product}")
    private String queueSendSaleToProduct;

    @Value("${mq.queues.saleToClient-demand}")
    private String queueSendSaleChargeToClient;

    @Bean
    public Queue queueSaleClient(){
        return new Queue(queueSendSaleToClient , true);
    }

    @Bean
    public Queue queueSalePayment(){
        return new Queue(queueSendSaleToPayment , true);
    }

    @Bean
    public Queue queueSaleProduct(){
        return new Queue(queueSendSaleToProduct , true);
    }

    @Bean
    public Queue queueSaleCharge(){
        return new Queue(queueSendSaleChargeToClient , true);
    }

}
