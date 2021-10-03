package ru.ev3nmorn.dto;

import ru.ev3nmorn.model.Cart;
import ru.ev3nmorn.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class CartDTO {

    private Integer id;
    private CustomerDTO customer;
    private List<ProductDTO> products;

    public CartDTO(Integer id, CustomerDTO customer, List<ProductDTO> products) {
        this.id = id;
        this.customer = customer;
        this.products = products;
    }

    public CartDTO(Cart cart, List<Product> products) {
        this.id = cart.getId();
        this.customer = new CustomerDTO(cart.getCustomer());
        this.products = products
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    public CartDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
