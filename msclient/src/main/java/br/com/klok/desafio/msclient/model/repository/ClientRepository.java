package br.com.klok.desafio.msclient.model.repository;

import br.com.klok.desafio.msclient.model.entity.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, String> {

    public Optional<ClientModel> findByEmail(String email);
}
