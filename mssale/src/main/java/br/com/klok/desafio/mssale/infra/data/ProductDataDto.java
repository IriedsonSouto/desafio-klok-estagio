package br.com.klok.desafio.mssale.infra.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ProductDataDto(@JsonProperty("sale_uuid") String saleUuid,
                             @JsonProperty("product_name") String productName,
                             @JsonProperty("product_uuid") String productUuid,
                             @JsonProperty("product_price") BigDecimal price,
                             Integer quantity) {

}