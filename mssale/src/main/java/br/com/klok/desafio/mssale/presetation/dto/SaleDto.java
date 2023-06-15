package br.com.klok.desafio.mssale.presetation.dto;

import br.com.klok.desafio.mssale.model.entity.SaleModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class SaleDto {

    @JsonProperty("email_client")
    @NotBlank(message ="Email client is required")
    private String emailClient;

    public SaleDto(SaleModel saleModel) {}

    public static SaleModel convertToModel(SaleDto saleDto) {
        return new SaleModel();
    }

}
