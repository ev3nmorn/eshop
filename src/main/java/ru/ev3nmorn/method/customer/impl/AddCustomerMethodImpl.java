package ru.ev3nmorn.method.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.ev3nmorn.dto.CustomerDTO;
import ru.ev3nmorn.dto.mapper.CustomerMapper;
import ru.ev3nmorn.model.Customer;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.customer.AddCustomerMethod;
import ru.ev3nmorn.repository.CustomerRepository;

@Component
public class AddCustomerMethodImpl implements AddCustomerMethod {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public AddCustomerMethodImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public void process(CustomerDTO customer) {
        if (existPhone(customer.getPhone())) {
            throw new ApiException("Customer with the same phone is already exist", HttpStatus.BAD_REQUEST, "AddCustomerMethod");
        }
        if (existEmail(customer.getEmail())) {
            throw new ApiException("Customer with the same email is already exist", HttpStatus.BAD_REQUEST, "AddCustomerMethod");
        }

        customerRepository.save(customerMapper.fromDTO(customer));
    }

    private boolean existPhone(String phone) {
        Customer customer = customerRepository.findByPhone(phone);

        return customer != null;
    }

    private boolean existEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);

        return customer != null;
    }
}
