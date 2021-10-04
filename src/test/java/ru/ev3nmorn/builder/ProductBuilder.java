package ru.ev3nmorn.builder;

import ru.ev3nmorn.model.Product;

public class ProductBuilder {

    private Product product;

    public ProductBuilder() {
        product = new Product();
    }

    public ProductBuilder defaultProduct() {
        product.setId(1);
        product.setName("test name");
        product.setDescription("test description");
        product.setPrice(100);

        return this;
    }

    public ProductBuilder withName(String name) {
        product.setName(name);

        return this;
    }

    public ProductBuilder withDescription(String description) {
        product.setDescription(description);

        return this;
    }

    public ProductBuilder withPrice(Double price) {
        product.setPrice(price);

        return this;
    }

    public ProductBuilder withId(Integer id) {
        product.setId(id);

        return this;
    }

    public Product build() {
        return  product;
    }

}
