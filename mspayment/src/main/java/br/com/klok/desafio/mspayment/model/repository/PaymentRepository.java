package br.com.klok.desafio.mspayment.model.repository;

import br.com.klok.desafio.mspayment.model.entity.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel, String> {

}
