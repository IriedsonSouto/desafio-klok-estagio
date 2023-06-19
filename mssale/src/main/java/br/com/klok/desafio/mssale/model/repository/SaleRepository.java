package br.com.klok.desafio.mssale.model.repository;

import br.com.klok.desafio.mssale.model.entity.SaleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SaleRepository extends JpaRepository<SaleModel, String> {

    List<SaleModel> findByClientId(String clientId);
}
