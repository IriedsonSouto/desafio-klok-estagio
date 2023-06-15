package br.com.klok.desafio.msproduct.model.entity;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "tb_product")
public class ProductModel {

    @Id
    @Column(name = "product_uuid")
    private String uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, scale = 2)
    private BigDecimal price;

    private String description;

    public ProductModel() {
        this.uuid= Generators.randomBasedGenerator().generate().toString();
    }
}
