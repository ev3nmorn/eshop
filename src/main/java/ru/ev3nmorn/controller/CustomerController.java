package ru.ev3nmorn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ev3nmorn.dto.CartDTO;
import ru.ev3nmorn.dto.CustomerDTO;
import ru.ev3nmorn.service.CartService;
import ru.ev3nmorn.service.CustomerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CartService cartService;

    @Autowired
    public CustomerController(CustomerService customerService, CartService cartService) {
        this.customerService = customerService;
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> allCustomers() {
        return customerService.allCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") Integer id) {
        return customerService.getCustomer(id);
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerDTO customer) {
        customerService.addCustomer(customer);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCustomer(@Valid @RequestBody CustomerDTO customer,
                                          @PathVariable("id") Integer id) {
        customerService.editCustomer(customer, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/cart")
    public ResponseEntity<List<CartDTO>> customerCarts(@PathVariable(name = "id") Integer id) {
        return cartService.getCustomerCarts(id);
    }

    @PostMapping("/{id}/cart")
    public ResponseEntity<?> addCart(@PathVariable("id") Integer id) {
        cartService.addCart(id);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}