package ru.ev3nmorn.method.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.ev3nmorn.dto.CustomerDTO;
import ru.ev3nmorn.dto.mapper.CustomerMapper;
import ru.ev3nmorn.method.customer.GetAllCustomersMethod;
import ru.ev3nmorn.repository.CustomerRepository;

import java.util.List;

@Component
public class GetAllCustomersMethodImpl implements GetAllCustomersMethod {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    @Autowired
    public GetAllCustomersMethodImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<List<CustomerDTO>> processToResponse() {
        return new ResponseEntity<>(customerMapper.toDTO(customerRepository.findAll()), HttpStatus.OK);
    }
}
