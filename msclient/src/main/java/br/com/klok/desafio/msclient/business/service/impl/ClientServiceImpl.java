package br.com.klok.desafio.msclient.business.service.impl;

import br.com.klok.desafio.msclient.business.service.ClientService;
import br.com.klok.desafio.msclient.infra.SendClient;
import br.com.klok.desafio.msclient.exception.EntityNotFoundException;
import br.com.klok.desafio.msclient.model.entity.ClientModel;
import br.com.klok.desafio.msclient.model.repository.ClientRepository;
import br.com.klok.desafio.msclient.presetation.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final SendClient queueSendClient;


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

        return optionalClient.orElseThrow( () -> new EntityNotFoundException("Client: " + uuid + " not found"));
    }

    @Override
    public ClientModel getClientByEmail(String email) {
        var optionalClient = clientRepository.findByEmail(email);

        return optionalClient.orElseThrow( () -> new EntityNotFoundException("Client: " + email + "not found"));
    }

    @Override
    public void sendClient(ClientModel clientModel) {
        try {
            this.queueSendClient.getClientToSend(clientModel);
        } catch (Exception e) {
            throw new EntityNotFoundException("Error internal");
        }
    }


    @Override
    public void deleteClientById(String id) {
        var client = this.getClientById(id);
        clientRepository.delete(client);
    }

}
