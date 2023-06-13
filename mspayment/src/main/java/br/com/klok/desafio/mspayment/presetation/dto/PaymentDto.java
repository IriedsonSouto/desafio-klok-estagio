package br.com.klok.desafio.mspayment.presetation.dto;

import br.com.klok.desafio.mspayment.model.entity.PaymentModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PaymentDto {

    private String uuid;

    public PaymentDto(PaymentModel paymentModel) {}

    public static PaymentModel convertToModel(PaymentDto paymentDto) {
        return new PaymentModel();
    }

}
