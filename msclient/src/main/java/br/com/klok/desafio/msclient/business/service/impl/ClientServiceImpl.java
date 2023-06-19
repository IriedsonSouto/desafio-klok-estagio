package br.com.klok.desafio.msclient.business.service.impl;

import br.com.klok.desafio.msclient.business.service.ClientService;
import br.com.klok.desafio.msclient.infra.PostRabbitClient;
import br.com.klok.desafio.msclient.exception.EntityNotFoundException;
import br.com.klok.desafio.msclient.infra.data.SaleDataDto;
import br.com.klok.desafio.msclient.model.entity.ClientModel;
import br.com.klok.desafio.msclient.model.repository.ClientRepository;
import br.com.klok.desafio.msclient.presetation.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final PostRabbitClient queuePostRabbitClient;


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
    public void sendClientToSale(SaleDataDto saleDataDto) {
        try {
            this.queuePostRabbitClient.postClientToSale(saleDataDto);
            log.info("Client " + saleDataDto.email() + " sent to mssale");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public ClientModel updateClient(String id, ClientDto clientDto) {
        var clientModel = getClientById(id);

        String name = clientDto.getName() == null ? clientModel.getName() : clientDto.getName();
        String email = clientDto.getEmail() == null ? clientModel.getEmail() : clientDto.getEmail();

        clientModel.setName(name);
        clientModel.setEmail(email);

        return clientRepository.save(clientModel);
    }


    @Override
    public void deleteClientById(String id) {
        var client = this.getClientById(id);
        clientRepository.delete(client);
    }

}
