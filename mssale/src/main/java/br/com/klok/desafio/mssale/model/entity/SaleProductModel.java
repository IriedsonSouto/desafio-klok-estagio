package br.com.klok.desafio.mssale.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity(name = "tb_sale_product")
@NoArgsConstructor
@AllArgsConstructor
public class SaleProductModel {

    @Id
    private String uuid;

    @Column(name = "name_product", nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "price_product", scale = 2, nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_uuid_FK", nullable = false)
    private SaleModel saleModel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleProductModel that = (SaleProductModel) o;
        return this.name.equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
