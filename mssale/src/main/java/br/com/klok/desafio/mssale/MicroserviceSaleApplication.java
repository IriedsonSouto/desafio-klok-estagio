package br.com.klok.desafio.mssale;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Api for Sale 1.0",
description = "Application is a microservice made to manage the sales of a product"))
@EnableRabbit
public class MicroserviceSaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSaleApplication.class, args);
	}

}
