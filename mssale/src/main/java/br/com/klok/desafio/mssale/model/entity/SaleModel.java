package br.com.klok.desafio.mssale.model.entity;

import br.com.klok.desafio.mssale.model.enums.SaleStatusEnum;
import com.fasterxml.uuid.Generators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Entity(name = "tb_sale")
public class SaleModel {

    @Id
    @Column(name = "client_uuid")
    private String uuid;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "order_date", nullable = false)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SaleStatusEnum status;

    @Column(nullable = false)
    private String produtcId;

    @Column(nullable = false)
    private String clientId;

    public SaleModel() {
        this.uuid = Generators.randomBasedGenerator().generate().toString();
    }
}
