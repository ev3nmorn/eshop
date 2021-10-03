package ru.ev3nmorn.method.customer;

import ru.ev3nmorn.dto.CustomerDTO;

public interface EditCustomerMethod {

    void process(CustomerDTO customer, Integer id);

}
