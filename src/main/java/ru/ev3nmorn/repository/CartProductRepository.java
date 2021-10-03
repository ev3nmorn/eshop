package ru.ev3nmorn.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ev3nmorn.model.CartProduct;

import java.util.List;

public interface CartProductRepository extends CrudRepository<CartProduct, Integer> {

    List<CartProduct> findByCartId(Integer id);

    List<CartProduct> findByCartIdAndProductId(Integer cartId, Integer productId);

}
