package ru.ev3nmorn.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ev3nmorn.model.Cart;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Integer> {

    List<Cart> findCartsByCustomerId(Integer id);

}
