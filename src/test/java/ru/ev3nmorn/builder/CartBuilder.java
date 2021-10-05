package ru.ev3nmorn.builder;

import ru.ev3nmorn.model.Cart;
import ru.ev3nmorn.model.CartProduct;
import ru.ev3nmorn.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CartBuilder {

    private Cart cart;

    public CartBuilder() {
        cart = new Cart();
    }

    public CartBuilder defaultCart() {
        cart.setCustomer(new CustomerBuilder()
                .defaultCustomer()
                .build());
        cart.setCartProduct(new ArrayList<>());
        cart.setId(1);

        return this;
    }

    public CartBuilder withId(Integer id) {
        cart.setId(id);

        return this;
    }

    public CartBuilder withCustomer(Customer customer) {
        cart.setCustomer(customer);

        return this;
    }

    public CartBuilder withProducts(List<CartProduct> products) {
        cart.setCartProduct(products);

        return this;
    }

    public Cart build() {
        return cart;
    }
}
