package ru.ev3nmorn.service;

import org.springframework.http.ResponseEntity;
import ru.ev3nmorn.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    ResponseEntity<List<CustomerDTO>> allCustomers();
    ResponseEntity<CustomerDTO> getCustomer(Integer id);
    void addCustomer(CustomerDTO customer);
    void deleteCustomer(Integer id);
    void editCustomer(CustomerDTO customer, Integer id);

}
