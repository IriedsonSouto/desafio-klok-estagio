package br.com.klok.desafio.mssale.presetation.dto;

import br.com.klok.desafio.mssale.model.entity.SaleModel;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class SaleDto {

    @NotBlank(message ="Client is required")
    private String clientId;

    public SaleDto(SaleModel saleModel) {
        this.clientId = saleModel.getClientId();
    }

    public static SaleModel convertToModel(SaleDto saleDto) {
        var saleModel =  new SaleModel();

        saleModel.setClientId(saleDto.clientId);

        return saleModel;
    }

}
