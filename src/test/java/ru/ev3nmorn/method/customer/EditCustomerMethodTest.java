package ru.ev3nmorn.method.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ev3nmorn.builder.CustomerBuilder;
import ru.ev3nmorn.dto.CustomerDTO;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.customer.impl.EditCustomerMethodImpl;
import ru.ev3nmorn.model.Customer;
import ru.ev3nmorn.repository.CustomerRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EditCustomerMethodTest {

    @Mock
    private CustomerRepository customerRepository;

    private EditCustomerMethod editCustomerMethod;

    private static final Integer ID = 1;
    private static final String PHONE_NUMBER = "8-777-888-99-99";
    private static final String EMAIL = "exists@mail.ru";

    @BeforeEach
    public void initMethod() {
        editCustomerMethod = new EditCustomerMethodImpl(customerRepository);
    }

    @Test
    public void idNotProvidedTest() {
        Customer customer = new CustomerBuilder()
                .defaultCustomer()
                .build();

        Exception exception = assertThrows(ApiException.class,
                () -> editCustomerMethod.process(new CustomerDTO(customer), null));
        assertEquals("Id is mandatory to update customer info", exception.getMessage());
    }

    @Test
    public void customerNotFoundTest() {
        Customer customer = new CustomerBuilder()
                .defaultCustomer()
                .build();

        Exception exception = assertThrows(ApiException.class,
                () -> editCustomerMethod.process(new CustomerDTO(customer), ID));
        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    public void existsCustomerPrhoneTest() {
        Customer customer = new CustomerBuilder()
                .defaultCustomer()
                .withId(ID)
                .build(),
        samePhoneCustomer = new CustomerBuilder()
                .defaultCustomer()
                .withId(ID + 1)
                .build();

        when(customerRepository.findById(ID))
                .thenReturn(Optional.of(customer));
        when(customerRepository.findByPhone(customer.getPhone()))
                .thenReturn(samePhoneCustomer);

        Exception exception = assertThrows(ApiException.class,
                () -> editCustomerMethod.process(new CustomerDTO(customer), ID));
        assertEquals("Customer with the same phone is already exist", exception.getMessage());
    }

    @Test
    public void existsCustomerEmailTest() {
        Customer customer = new CustomerBuilder()
                .defaultCustomer()
                .withId(ID)
                .build(),
                sameEmailCustomer = new CustomerBuilder()
                        .defaultCustomer()
                        .withId(ID + 1)
                        .build();

        when(customerRepository.findById(ID))
                .thenReturn(Optional.of(customer));
        when(customerRepository.findByEmail(customer.getEmail()))
                .thenReturn(sameEmailCustomer);

        Exception exception = assertThrows(ApiException.class,
                () -> editCustomerMethod.process(new CustomerDTO(customer), ID));
        assertEquals("Customer with the same email is already exist", exception.getMessage());
    }

    @Test
    public void editCustomerTest() {
        Customer customer = new CustomerBuilder()
                .defaultCustomer()
                .withId(ID)
                .build();

        when(customerRepository.findById(ID))
                .thenReturn(Optional.of(customer));

        editCustomerMethod.process(new CustomerDTO(customer), ID);

        verify(customerRepository, times(1))
                .save(any());
    }
}
