package br.com.klok.desafio.mssale.presetation.controller;

import br.com.klok.desafio.mssale.business.SaleService;
import br.com.klok.desafio.mssale.infra.data.ConsultProductDto;
import br.com.klok.desafio.mssale.infra.data.ProductDataDto;
import br.com.klok.desafio.mssale.model.entity.SaleModel;
import br.com.klok.desafio.mssale.presetation.dto.SaleDto;
import br.com.klok.desafio.mssale.presetation.dto.SaleWithProductDto;
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
    public ResponseEntity createSale(@Valid @RequestBody SaleDto saleDto){
        saleService.createSale(saleDto);
        return ResponseEntity.status(200).body("Sucessofully");
    }

    @GetMapping
    public ResponseEntity<List<SaleModel>> getAllSales(){
        return ResponseEntity.status(200).body(saleService.getAllSale());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<SaleWithProductDto> getSaleById(@PathVariable("id") String id){
        return ResponseEntity.status(200).body(saleService.getSaleProductById(id));
    }

    @GetMapping("/get-by-client-id/{id}")
    public ResponseEntity<List<SaleModel>> getSaleByClientId(@PathVariable("id") String id){
        return ResponseEntity.status(200).body(saleService.getSaleByClientId(id));
    }

    @PutMapping("/update-list-product/{id}")
    public ResponseEntity<SaleModel> updateSaleProductList(@PathVariable("id") String id, @RequestBody ConsultProductDto consultProductDto){

        return ResponseEntity.status(200).body(saleService.consultProductToUpdateList(id, consultProductDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSaleById(@PathVariable("id") String id){
        saleService.deleteSaleById(id);
        return ResponseEntity.status(200).body("Sucessofully deleted!");
    }

}

