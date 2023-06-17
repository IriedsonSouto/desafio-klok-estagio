package br.com.klok.desafio.mssale.infra.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConsultProductDto(@JsonProperty("product_uuid") String productUuid,
                                Integer quantity) {

}