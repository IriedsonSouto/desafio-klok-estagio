package br.com.klok.desafio.msproduct.business.service;

import br.com.klok.desafio.msproduct.infra.data.ProductSaleDataDto;
import br.com.klok.desafio.msproduct.model.entity.ProductModel;
import br.com.klok.desafio.msproduct.presetation.dto.ProductDto;

import java.util.List;

public interface ProductService {

    public ProductModel saveProduct(ProductDto Product);

    public List<ProductModel> getAllProduct();

    public ProductModel getProductById(String id);

    public void sendProductToSale(ProductSaleDataDto saleDataDto);

    public ProductModel updateProduct(String id, ProductDto client);

    public void deleteProductById(String id);

}
