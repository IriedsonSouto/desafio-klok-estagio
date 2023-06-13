package br.com.klok.desafio.mscloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayMicroserviceApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {

		return builder.
					routes()
						.route( r -> r.path("/clients/**").uri("lb://msclient"))
						.route( r -> r.path("/sales/**").uri("lb://mssale"))
						.route( r -> r.path("/payments/**").uri("lb://mspayment"))
					.build();
	}

}
