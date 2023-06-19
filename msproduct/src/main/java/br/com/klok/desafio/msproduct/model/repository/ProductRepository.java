package br.com.klok.desafio.msproduct.model.repository;

import br.com.klok.desafio.msproduct.model.entity.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, String> {

}
