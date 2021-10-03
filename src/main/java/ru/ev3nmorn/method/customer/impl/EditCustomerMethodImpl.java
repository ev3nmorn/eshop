package ru.ev3nmorn.method.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.ev3nmorn.dto.CustomerDTO;
import ru.ev3nmorn.model.Customer;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.customer.EditCustomerMethod;
import ru.ev3nmorn.repository.CustomerRepository;

@Component
public class EditCustomerMethodImpl implements EditCustomerMethod {

    private final CustomerRepository customerRepository;

    @Autowired
    public EditCustomerMethodImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void process(CustomerDTO customer, Integer id) {
        if (id == null) {
            throw new ApiException("Id is mandatory to update customer info", HttpStatus.BAD_REQUEST, "EditCustomerMethod");
        }

        Customer customerToUpdate = customerRepository.findById(id).
                orElseThrow(() -> new ApiException("Customer not found", HttpStatus.NOT_FOUND, "EditCustomerMethod"));

        if (existPhone(customer.getPhone(), id)) {
            throw new ApiException("Customer with the same phone is already exist", HttpStatus.BAD_REQUEST, "EditCustomerMethod");
        }
        if (existEmail(customer.getEmail(), id)) {
            throw new ApiException("Customer with the same email is already exist", HttpStatus.BAD_REQUEST, "EditCustomerMethod");
        }

        customerToUpdate.setName(customer.getName());
        customerToUpdate.setPhone(customer.getPhone());
        customerToUpdate.setEmail(customer.getEmail());

        customerRepository.save(customerToUpdate);
    }

    private boolean existPhone(String phone, Integer id) {
        Customer customer = customerRepository.findByPhone(phone);

        return customer != null && !customer.getId().equals(id);
    }

    private boolean existEmail(String email, Integer id) {
        Customer customer = customerRepository.findByEmail(email);

        return customer != null && !customer.getId().equals(id);
    }
}
