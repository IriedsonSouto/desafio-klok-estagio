package br.com.klok.desafio.mspayment.infra.data;


import br.com.klok.desafio.mspayment.model.enums.PaymentMethodEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

public record SalePaymentData(@JsonProperty("sale_uuid") String uuid
                            , @JsonProperty("payment_method") PaymentMethodEnum method){

}
