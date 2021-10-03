package ru.ev3nmorn.dto.mapper;

import org.springframework.stereotype.Component;
import ru.ev3nmorn.dto.ProductDTO;
import ru.ev3nmorn.model.Product;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public List<ProductDTO> toDTO(List<Product> products) {
        return products
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    public ProductDTO toDTO(Product product) {
        return new ProductDTO(product);
    }

    public Product fromDTO(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());

        return product;
    }
}
