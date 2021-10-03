package ru.ev3nmorn.dto.mapper;

import org.springframework.stereotype.Component;
import ru.ev3nmorn.model.Customer;
import ru.ev3nmorn.dto.CustomerDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public List<CustomerDTO> toDTO(List<Customer> customers) {
        return customers
                .stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toList());
    }

    public CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(customer);
    }

    public Customer fromDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();

        customer.setName(customerDTO.getName());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());

        return customer;
    }
}
