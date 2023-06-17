package br.com.klok.desafio.msclient.presetation.controller;

import br.com.klok.desafio.msclient.business.service.ClientService;
import br.com.klok.desafio.msclient.model.entity.ClientModel;
import br.com.klok.desafio.msclient.presetation.dto.ClientDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientModel> createClient(@Valid @RequestBody ClientDto clientDto){
        return ResponseEntity.status(201).body(clientService.saveClient(clientDto));
    }

    @GetMapping
    public ResponseEntity<List<ClientModel>> getAllClients(){
        return ResponseEntity.status(200).body(clientService.getAllClient());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ClientModel> getClientById(@PathVariable("id") String id){
        return ResponseEntity.status(200).body(clientService.getClientById(id));
    }

    @GetMapping("/get-by-email/{email}")
    public ResponseEntity<ClientModel> getClientByEmail(@PathVariable("email") String email){
        return ResponseEntity.status(200).body(clientService.getClientByEmail(email));
    }

    @PutMapping("{id}")
    public ResponseEntity<ClientModel> updateClientById(@PathVariable("id") String id, @Valid @RequestBody ClientDto clientDto){
        return ResponseEntity.status(200).body(clientService.updateClient(id, clientDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable("id") String id){
        clientService.deleteClientById(id);
        return ResponseEntity.status(200).body("Sucessofully deleted!");
    }

}

