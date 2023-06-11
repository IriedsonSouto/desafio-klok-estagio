package br.com.klok.desafio.mssale.presetation.dto;

import br.com.klok.desafio.mssale.model.entity.SaleModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class SaleDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date date;

    @NotBlank(message ="Product is required")
    private String produtcId;

    @NotBlank(message ="Client is required")
    private String clientId;

    public SaleDto(SaleModel saleModel) {
        this.date = saleModel.getDate();
        this.clientId = saleModel.getClientId();
        this.produtcId = saleModel.getProdutcId();
    }

    public static SaleModel convertToModel(SaleDto saleDto) {
        var saleModel =  new SaleModel();

        saleModel.setDate(saleDto.getDate());
        saleModel.setProdutcId(saleDto.getProdutcId());
        saleModel.setClientId(saleDto.clientId);

        return saleModel;
    }

}
