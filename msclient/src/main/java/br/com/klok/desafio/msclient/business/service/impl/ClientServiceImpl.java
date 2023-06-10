package br.com.klok.desafio.msclient.business.service.impl;

import br.com.klok.desafio.msclient.business.ClientService;
import br.com.klok.desafio.msclient.exception.EntityNotFoundException;
import br.com.klok.desafio.msclient.model.entity.ClientModel;
import br.com.klok.desafio.msclient.model.repository.ClientRepository;
import br.com.klok.desafio.msclient.presetation.dto.ClientDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public ClientModel saveClient(ClientDto clientDto) {
        return clientRepository.save(ClientDto.convertToModel(clientDto));
    }

    @Override
    public List<ClientModel> getAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public ClientModel getClientById(String uuid) {
        var optionalClient = clientRepository.findById(uuid);

        if (optionalClient.isEmpty()) {
            throw new EntityNotFoundException("Client: " + uuid + " not found");
        }
        return optionalClient.get();
    }

    @Override
    public ClientModel getClientByEmail(String email) {
        var optionalClient = clientRepository.findByEmail(email);

        if (optionalClient.isEmpty()) {
            throw new EntityNotFoundException("Client: " + email + "not found");
        }
        return optionalClient.get();
    }

    @Override
    public void deleteClientById(String id) {
        var client = this.getClientById(id);
        clientRepository.delete(client);
    }

}
