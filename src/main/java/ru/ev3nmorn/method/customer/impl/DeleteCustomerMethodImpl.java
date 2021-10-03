package ru.ev3nmorn.method.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.customer.DeleteCustomerMethod;
import ru.ev3nmorn.repository.CustomerRepository;

@Component
public class DeleteCustomerMethodImpl implements DeleteCustomerMethod {

    private final CustomerRepository customerRepository;

    @Autowired
    public DeleteCustomerMethodImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void process(Integer id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new ApiException("Customer not found", HttpStatus.NOT_FOUND, "DeleteCustomerMethod");
        }
    }
}
