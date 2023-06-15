package br.com.klok.desafio.msproduct.presetation.dto;

import br.com.klok.desafio.msproduct.model.entity.ProductModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    @NotBlank(message ="Name is required")
    @Size(message = "Name must be at least 3 characters",min = 3)
    private String name;

    @NotNull(message ="Price is required")
    private BigDecimal price;

    private String description;


    public ProductDto(ProductModel productModel) {
        this.name = productModel.getName();
        this.price = productModel.getPrice();
        this.description = productModel.getDescription();
    }

    public static ProductModel  convertToModel(ProductDto productDto) {
        var productModel  =  new ProductModel();
        productModel.setName(productDto.getName());
        productModel.setPrice(productDto.getPrice());
        productModel.setDescription(productDto.getDescription());

        return productModel;
    }

}
