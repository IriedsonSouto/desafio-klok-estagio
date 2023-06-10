package br.com.klok.desafio.msclient.business;

import br.com.klok.desafio.msclient.model.entity.ClientModel;
import br.com.klok.desafio.msclient.presetation.dto.ClientDto;

import java.util.List;

public interface ClientService {


    public ClientModel saveClient(ClientDto client);

    public List<ClientModel> getAllClient();

    public ClientModel getClientById(String id);

    public ClientModel getClientByEmail(String email);

    public void deleteClientById(String id);

}
