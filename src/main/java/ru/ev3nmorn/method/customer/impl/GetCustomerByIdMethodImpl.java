package ru.ev3nmorn.method.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.ev3nmorn.model.Customer;
import ru.ev3nmorn.dto.CustomerDTO;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.customer.GetCustomerByIdMethod;
import ru.ev3nmorn.repository.CustomerRepository;

@Component
public class GetCustomerByIdMethodImpl implements GetCustomerByIdMethod {

    private final CustomerRepository customerRepository;

    @Autowired
    public GetCustomerByIdMethodImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<CustomerDTO> processToResponse(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ApiException("Customer not found", HttpStatus.NOT_FOUND, "GetCustomerByIdMethod"));
        return new ResponseEntity<>(new CustomerDTO(customer), HttpStatus.OK);
    }
}
