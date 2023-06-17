package br.com.klok.desafio.msproduct.business.service.impl;

import br.com.klok.desafio.msproduct.exception.EntityNotFoundException;
import br.com.klok.desafio.msproduct.infra.PostRabbitClient;
import br.com.klok.desafio.msproduct.business.service.ProductService;
import br.com.klok.desafio.msproduct.infra.data.ProductSaleDataDto;
import br.com.klok.desafio.msproduct.model.entity.ProductModel;
import br.com.klok.desafio.msproduct.model.repository.ProductRepository;
import br.com.klok.desafio.msproduct.presetation.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final PostRabbitClient queuePostRabbitProduct;


    @Override
    public ProductModel saveProduct(ProductDto productDto) {
        return productRepository.save(ProductDto.convertToModel(productDto));
    }

    @Override
    public List<ProductModel> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public ProductModel getProductById(String uuid) {
        var optionalProduct = productRepository.findById(uuid);

        return optionalProduct.orElseThrow( () -> new EntityNotFoundException("Product: " + uuid + " not found"));
    }

    @Override
    public void sendProductToSale(ProductSaleDataDto productSaleDataDto) {
        try {
            this.queuePostRabbitProduct.postProductToSale(productSaleDataDto);
            log.info("Send product to mssale: " + productSaleDataDto.productName() + " " + productSaleDataDto.productUuid());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public ProductModel updateProduct(String id, ProductDto productDto) {
        var productModel = getProductById(id);

        String name = productDto.getName() == null ? productModel.getName() : productDto.getName();
        String description = productDto.getDescription() == null ? productModel.getDescription() : productDto.getDescription();
        BigDecimal price = productDto.getPrice() == null ? productModel.getPrice() : productDto.getPrice();

        productModel.setName(name);
        productModel.setDescription(description);
        productModel.setPrice(price);

        return productRepository.save(productModel);
    }

    @Override
    public void deleteProductById(String id) {
        var product = this.getProductById(id);
        productRepository.delete(product);
    }

}
