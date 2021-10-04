package ru.ev3nmorn.method.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ev3nmorn.builder.CustomerBuilder;
import ru.ev3nmorn.dto.CustomerDTO;
import ru.ev3nmorn.dto.mapper.CustomerMapper;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.customer.impl.AddCustomerMethodImpl;
import ru.ev3nmorn.model.Customer;
import ru.ev3nmorn.repository.CustomerRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AddCustomerMethodTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;

    private AddCustomerMethod addCustomerMethod;

    private static final String PHONE_NUMBER = "8-777-888-99-99";
    private static final String EMAIL = "exists@mail.ru";

    @BeforeEach
    public void initMethod() {
        addCustomerMethod = new AddCustomerMethodImpl(customerRepository, customerMapper);
    }

    @Test
    public void customerPhoneAlreadyExistsTest() {
        Customer customer = new CustomerBuilder()
                .defaultCustomer()
                .withPhone(PHONE_NUMBER)
                .build();

        when(customerRepository.findByPhone(PHONE_NUMBER))
                .thenReturn(customer);

        Exception exception = assertThrows(ApiException.class,
                () -> addCustomerMethod.process(new CustomerDTO(customer)));
        assertEquals("Customer with the same phone is already exist", exception.getMessage());
    }

    @Test
    public void customerEmailAlreadyExistsTest() {
        Customer customer = new CustomerBuilder()
                .defaultCustomer()
                .withEmail(EMAIL)
                .build();

        when(customerRepository.findByEmail(EMAIL))
                .thenReturn(customer);

        Exception exception = assertThrows(ApiException.class,
                () -> addCustomerMethod.process(new CustomerDTO(customer)));
        assertEquals("Customer with the same email is already exist", exception.getMessage());
    }

    @Test
    public void addCustomerTest() {
        Customer customer = new CustomerBuilder()
                .defaultCustomer()
                .build();

        addCustomerMethod.process(new CustomerDTO(customer));

        verify(customerRepository, times(1))
                .save(any());
    }
}
