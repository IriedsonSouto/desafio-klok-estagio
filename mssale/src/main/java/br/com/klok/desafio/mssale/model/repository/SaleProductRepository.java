package br.com.klok.desafio.mssale.model.repository;

import br.com.klok.desafio.mssale.model.entity.SaleProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleProductRepository extends JpaRepository<SaleProductModel, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM tb_sale_product WHERE sale_uuid_FK=:saleId")
    List<SaleProductModel> getSaleProductsBySaleId(String saleId);

}
