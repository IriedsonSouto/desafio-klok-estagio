package br.com.klok.desafio.mssale.model.entity;

import br.com.klok.desafio.mssale.model.enums.SaleStatusEnum;
import com.fasterxml.uuid.Generators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "tb_sale")
public class SaleModel {

    @Id
    @Column(name = "sale_uuid")
    private String uuid;

    @Column(name = "sale_create_date", nullable = false)
    private Date createDate = new Date();

    @Column(name = "price_sale", scale = 2)
    private BigDecimal price;

    @Column(name = "sale_paid_date")
    private Date paidDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SaleStatusEnum status;

    @OneToMany(mappedBy = "saleModel", cascade = CascadeType.ALL)
    private List<SaleProductModel> saleProdutcList;

    @Column(name ="client_uuid_FK", nullable = false)
    private String clientId;

    public SaleModel() {
        this.uuid = Generators.randomBasedGenerator().generate().toString();
    }
}
