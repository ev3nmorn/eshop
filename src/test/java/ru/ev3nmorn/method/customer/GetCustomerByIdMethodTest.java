package ru.ev3nmorn.method.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ev3nmorn.builder.CustomerBuilder;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.customer.impl.GetCustomerByIdMethodImpl;
import ru.ev3nmorn.model.Customer;
import ru.ev3nmorn.repository.CustomerRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GetCustomerByIdMethodTest {

    @Mock
    private CustomerRepository customerRepository;

    private GetCustomerByIdMethod getCustomerByIdMethod;

    private static final Integer ID = 1;

    @BeforeEach
    public void initMethod() {
        getCustomerByIdMethod = new GetCustomerByIdMethodImpl(customerRepository);
    }

    @Test
    public void customerNotFoundTest() {
        Exception exception = assertThrows(ApiException.class,
                () -> getCustomerByIdMethod.processToResponse(ID));
        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    public void findCustomerByIdTest() {
        Customer customer = new CustomerBuilder()
                .defaultCustomer()
                .withId(ID)
                .build();

        when(customerRepository.findById(ID))
                .thenReturn(Optional.of(customer));

        ResponseEntity<?> response = getCustomerByIdMethod.processToResponse(ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
