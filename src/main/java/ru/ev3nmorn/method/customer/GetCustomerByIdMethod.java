package ru.ev3nmorn.method.customer;

import org.springframework.http.ResponseEntity;
import ru.ev3nmorn.dto.CustomerDTO;

public interface GetCustomerByIdMethod {

    ResponseEntity<CustomerDTO> processToResponse(Integer id);

}
