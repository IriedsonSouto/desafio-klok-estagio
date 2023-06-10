package br.com.klok.desafio.msclient.presetation.dto;

import br.com.klok.desafio.msclient.model.entity.ClientModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDto {

    @NotBlank(message ="Name is required")
    @Size(message = "Name must be at least 3 characters",min = 3)
    private String name;

    @NotBlank(message ="Email is required")
    @Email(message ="Email must have a valid format")
    private String email;

    public ClientDto(ClientModel clientModel) {
        this.name = clientModel.getName();
        this.email = clientModel.getEmail();
    }

    public static ClientModel convertToModel(ClientDto clientDto) {
        var clientModel =  new ClientModel();
        clientModel.setName(clientDto.getName());
        clientModel.setEmail(clientDto.getEmail());

        return clientModel;
    }

}
