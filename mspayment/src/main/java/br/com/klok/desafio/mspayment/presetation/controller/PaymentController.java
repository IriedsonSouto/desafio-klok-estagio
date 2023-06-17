package br.com.klok.desafio.mspayment.presetation.controller;

import br.com.klok.desafio.mspayment.business.PaymentService;
import br.com.klok.desafio.mspayment.model.entity.PaymentModel;
import br.com.klok.desafio.mspayment.presetation.dto.PaymentDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity createPayment(@Valid @RequestBody PaymentDto paymentDto){
        paymentService.createPayment(paymentDto);
        return ResponseEntity.status(200).body("Sucessofully");
    }

    @GetMapping
    public ResponseEntity<List<PaymentModel>> getAllPayment(){
        return ResponseEntity.status(200).body(paymentService.getAllPayment());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<PaymentModel> getPaymentById(@PathVariable("id") String id){
        return ResponseEntity.status(200).body(paymentService.getPaymentById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity updatePaymentById(@PathVariable("id") String id, @Valid @RequestBody PaymentDto paymentDto){
        return ResponseEntity.status(200).body(paymentService.updatePaymentById(id, paymentDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePaymentById(@PathVariable("id") String id){
        paymentService.deletePaymentById(id);
        return ResponseEntity.status(200).body("Sucessofully deleted!");
    }

}

