package ru.ev3nmorn.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ev3nmorn.model.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findByPhone(String phone);
    Customer findByEmail(String email);
    List<Customer> findAll();

}
