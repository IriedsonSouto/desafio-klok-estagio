package br.com.klok.desafio.mssale.infra.data;


import com.fasterxml.jackson.annotation.JsonProperty;

public record ClientDataDto(@JsonProperty("client_uuid") String uuid,
                            @JsonProperty("client_name") String name,
                            @JsonProperty("email_client") String email) {

}
