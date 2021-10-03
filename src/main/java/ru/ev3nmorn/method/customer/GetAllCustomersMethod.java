package ru.ev3nmorn.method.customer;

import org.springframework.http.ResponseEntity;
import ru.ev3nmorn.dto.CustomerDTO;

import java.util.List;

public interface GetAllCustomersMethod {

    ResponseEntity<List<CustomerDTO>> processToResponse();

}
