package ru.ev3nmorn.method.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ev3nmorn.builder.CustomerBuilder;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.cart.impl.AddCartMethodImpl;
import ru.ev3nmorn.model.Customer;
import ru.ev3nmorn.repository.CartRepository;
import ru.ev3nmorn.repository.CustomerRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AddCartMethodTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private CustomerRepository customerRepository;

    private AddCartMethod addCartMethod;

    private static final Integer CUSTOMER_ID = 1;

    @BeforeEach
    public void initMethod() {
        addCartMethod = new AddCartMethodImpl(cartRepository, customerRepository);
    }

    @Test
    public void customerNotFoundTest() {
        Exception exception = assertThrows(ApiException.class,
                () -> addCartMethod.process(CUSTOMER_ID));

        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    public void addCartTest() {
        Customer customer = new CustomerBuilder()
                .defaultCustomer()
                .withId(CUSTOMER_ID)
                .build();

        when(customerRepository.findById(CUSTOMER_ID))
                .thenReturn(Optional.of(customer));

        addCartMethod.process(CUSTOMER_ID);

        verify(cartRepository, times(1))
                .save(any());
    }
}
