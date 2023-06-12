package br.com.klok.desafio.mssale.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "tb_sale_product")
@NoArgsConstructor
@AllArgsConstructor
public class SaleProductModel {

    @Id
    private String uuid;
    private String name;
    private Integer quantity;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_uuid_FK", nullable = false)
    private SaleModel saleModel;

}
