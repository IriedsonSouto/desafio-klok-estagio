package br.com.klok.desafio.mssale.infra.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentDataDto(@JsonProperty("sale_uuid") String uuidSale,
                             @JsonProperty("payment_method") String method) {





}