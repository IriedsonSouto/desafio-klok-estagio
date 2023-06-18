package br.com.klok.desafio.msproduct.presetation.controller;

import br.com.klok.desafio.msproduct.business.service.ProductService;
import br.com.klok.desafio.msproduct.model.entity.ProductModel;
import br.com.klok.desafio.msproduct.presetation.dto.ProductDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductModel> createProduct(@Valid @RequestBody ProductDto productDto){
        return ResponseEntity.status(201).body(productService.saveProduct(productDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return ResponseEntity.status(200).body(productService.getAllProduct());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable("id") String id){
        return ResponseEntity.status(200).body(productService.getProductById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductModel> updateProductById(@PathVariable("id") String id, @Valid @RequestBody ProductDto productDto){
        return ResponseEntity.status(200).body(productService.updateProduct(id, productDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") String id){
        productService.deleteProductById(id);
        return ResponseEntity.status(200).body("Successfully deleted!");
    }

}

