package br.com.klok.desafio.mspayment.model.entity;

import br.com.klok.desafio.mssale.model.enums.PaymentMethodEnum;
import com.fasterxml.uuid.Generators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "tb_payment")
public class PaymentModel {

    @Id
    @Column(name = "payment_uuid")
    private String uuid;

    @Column(name = "payment_create_date", nullable = false)
    private Date createDate = new Date();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethodEnum method;

    @Column(name ="Sale_uuid_FK", nullable = false)
    private String saleId;

    public PaymentModel() {
        this.uuid = Generators.randomBasedGenerator().generate().toString();
    }
}
