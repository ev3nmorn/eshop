package ru.ev3nmorn.method.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ev3nmorn.dto.mapper.CustomerMapper;
import ru.ev3nmorn.method.customer.impl.GetAllCustomersMethodImpl;
import ru.ev3nmorn.method.product.impl.GetAllProductsMethodImpl;
import ru.ev3nmorn.repository.CustomerRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GetAllCustomersMethodTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;

    private GetAllCustomersMethod getAllProductsMethod;

    @BeforeEach
    public void initMethod() {
        getAllProductsMethod = new GetAllCustomersMethodImpl(customerMapper, customerRepository);
    }

    @Test
    public void allCustomereTest() {
        ResponseEntity<?> response = getAllProductsMethod.processToResponse();

        verify(customerRepository, times(1))
                .findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
