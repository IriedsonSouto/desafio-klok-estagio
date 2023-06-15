package br.com.klok.desafio.mssale.presetation.dto;

import br.com.klok.desafio.mssale.model.entity.SaleModel;
import br.com.klok.desafio.mssale.model.entity.SaleProductModel;
import br.com.klok.desafio.mssale.model.enums.SaleStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SaleWithProductDto {

    private String uuid;
    private Date createDate;
    private BigDecimal price;
    private Date paidDate;
    private SaleStatusEnum status;
    private String clientId;
    private List<SaleProductModel> products;

    public SaleWithProductDto(SaleModel saleModel){
        this.uuid = saleModel.getUuid();
        this.createDate = saleModel.getCreateDate();
        this.price = saleModel.getPrice();
        this.paidDate = saleModel.getPaidDate();
        this.status = saleModel.getStatus();
        this.clientId = saleModel.getClientId();
    }

}
