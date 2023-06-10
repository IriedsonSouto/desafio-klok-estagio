package br.com.klok.desafio.msclient.model.entity;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tb_client")
public class ClientModel {

    @Id
    @Column(name = "client_uuid")
    private String uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    public ClientModel() {
        this.uuid= Generators.randomBasedGenerator().generate().toString();
    }
}
