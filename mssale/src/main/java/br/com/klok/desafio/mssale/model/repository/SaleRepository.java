package br.com.klok.desafio.mssale.model.repository;

import br.com.klok.desafio.mssale.model.entity.SaleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SaleRepository extends JpaRepository<SaleModel, String> {

    Optional<SaleModel> findByClientId(String clientId);
}
