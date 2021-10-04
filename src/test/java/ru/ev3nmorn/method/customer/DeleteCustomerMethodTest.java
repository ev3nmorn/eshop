package ru.ev3nmorn.method.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.customer.impl.DeleteCustomerMethodImpl;
import ru.ev3nmorn.repository.CustomerRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DeleteCustomerMethodTest {

    @Mock
    private CustomerRepository customerRepository;

    private DeleteCustomerMethod deleteCustomerMethod;

    private static final Integer ID = 1;

    @BeforeEach
    public void initMethod() {
        deleteCustomerMethod = new DeleteCustomerMethodImpl(customerRepository);
    }

    @Test
    public void customerNotFoundTest() {
        Exception exception = assertThrows(ApiException.class, () -> deleteCustomerMethod.process(ID));

        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    public void deleteCustomerTest() {
        when(customerRepository.existsById(ID))
                .thenReturn(true);

        deleteCustomerMethod.process(ID);

        verify(customerRepository, times(1))
                .deleteById(ID);
    }

}
