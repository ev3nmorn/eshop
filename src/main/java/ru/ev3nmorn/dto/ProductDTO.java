package ru.ev3nmorn.dto;

import ru.ev3nmorn.model.Product;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProductDTO {

    private Integer id;

    @NotNull(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price should be positive number")
    private Double price;

    public ProductDTO(Integer id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
