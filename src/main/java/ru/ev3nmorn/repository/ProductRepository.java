package ru.ev3nmorn.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ev3nmorn.model.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Product findByName(String name);
    List<Product> findAll();

}
