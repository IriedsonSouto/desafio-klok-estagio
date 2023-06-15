package br.com.klok.desafio.msproduct.infra.data;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ProductSaleDataDto(@JsonProperty("sale_uuid") String saleUuid,
                                 @JsonProperty("product_name") String productName,
                                 @JsonProperty("product_uuid") String productUuid,
                                 @JsonProperty("product_price") BigDecimal price,
                                 Integer quantity){

}
