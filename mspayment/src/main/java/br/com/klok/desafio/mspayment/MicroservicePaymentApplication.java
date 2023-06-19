package br.com.klok.desafio.mspayment;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Api for Payment 1.0",
description = "Application is a microservice made to manage the payment of a sale"))
@EnableRabbit
public class MicroservicePaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePaymentApplication.class, args);
	}

}
