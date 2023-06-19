package br.com.klok.desafio.mspayment.presetation.dto;

import br.com.klok.desafio.mspayment.model.entity.PaymentModel;
import br.com.klok.desafio.mspayment.model.enums.PaymentMethodEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PaymentDto {

    @JsonProperty("sale_uuid")
    @NotBlank(message = "Sale UUID is required")
    private String uuidSale;

    @JsonProperty("payment_method")
    @NotNull(message = "Payment Method is required")
    private PaymentMethodEnum method;

    public PaymentDto(PaymentModel paymentModel) {
        this.uuidSale = paymentModel.getSaleId();
        this.method = paymentModel.getMethod();
    }

    public static PaymentModel convertToModel(PaymentDto paymentDto) {
        var paymentModel = new PaymentModel();
        paymentModel.setSaleId(paymentDto.getUuidSale());
        paymentModel.setMethod(paymentDto.getMethod());

        return paymentModel;
    }

}
