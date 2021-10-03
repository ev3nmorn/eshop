package ru.ev3nmorn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.ev3nmorn.dto.CustomerDTO;
import ru.ev3nmorn.method.customer.*;
import ru.ev3nmorn.service.CustomerService;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final GetAllCustomersMethod getAllCustomersMethod;
    private final GetCustomerByIdMethod getCustomerByIdMethod;
    private final AddCustomerMethod addCustomerMethod;
    private final DeleteCustomerMethod deleteCustomerMethod;
    private final EditCustomerMethod editCustomerMethod;

    @Autowired
    public CustomerServiceImpl(GetAllCustomersMethod getAllCustomersMethod, GetCustomerByIdMethod getCustomerByIdMethod,
                               AddCustomerMethod addCustomerMethod, DeleteCustomerMethod deleteCustomerMethod,
                               EditCustomerMethod editCustomerMethod) {
        this.getAllCustomersMethod = getAllCustomersMethod;
        this.getCustomerByIdMethod = getCustomerByIdMethod;
        this.addCustomerMethod = addCustomerMethod;
        this.deleteCustomerMethod = deleteCustomerMethod;
        this.editCustomerMethod = editCustomerMethod;
    }

    public ResponseEntity<List<CustomerDTO>> allCustomers() {
        return getAllCustomersMethod.processToResponse();
    }

    public ResponseEntity<CustomerDTO> getCustomer(Integer id) {
        return getCustomerByIdMethod.processToResponse(id);
    }

    public void addCustomer(CustomerDTO customer) {
        addCustomerMethod.process(customer);
    }

    public void deleteCustomer(Integer id) {
        deleteCustomerMethod.process(id);
    }

    public void editCustomer(CustomerDTO customer, Integer id) {
        editCustomerMethod.process(customer, id);
    }
}
