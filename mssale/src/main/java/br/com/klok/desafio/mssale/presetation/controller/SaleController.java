package br.com.klok.desafio.mssale.presetation.controller;

import br.com.klok.desafio.mssale.business.SaleService;
import br.com.klok.desafio.mssale.model.entity.SaleModel;
import br.com.klok.desafio.mssale.presetation.dto.SaleDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleModel> createSale(@Valid @RequestBody SaleDto saleDto){
        return ResponseEntity.status(201).body(saleService.saveSale(saleDto));
    }

    @GetMapping
    public ResponseEntity<List<SaleModel>> getAllSales(){
        return ResponseEntity.status(200).body(saleService.getAllSale());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<SaleModel> getSaleById(@PathVariable("id") String id){
        return ResponseEntity.status(200).body(saleService.getSaleById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSaleById(@PathVariable("id") String id){
        saleService.deleteSaleById(id);
        return ResponseEntity.status(200).body("Sucessofully deleted!");
    }

}

