package ru.ev3nmorn.method.cart.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.ev3nmorn.model.Cart;
import ru.ev3nmorn.model.Customer;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.cart.AddCartMethod;
import ru.ev3nmorn.repository.CartRepository;
import ru.ev3nmorn.repository.CustomerRepository;

@Component
public class AddCartMethodImpl implements AddCartMethod {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AddCartMethodImpl(CartRepository cartRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    public void process(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ApiException("Customer not found", HttpStatus.NOT_FOUND, "AddCartMethod"));

        cartRepository.save(new Cart(customer));
    }
}
