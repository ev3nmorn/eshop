package ru.ev3nmorn.dto.mapper;

import org.springframework.stereotype.Component;
import ru.ev3nmorn.model.Cart;
import ru.ev3nmorn.dto.CartDTO;
import ru.ev3nmorn.model.CartProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    public List<CartDTO> toDTO(Map<Cart, List<CartProduct>> cartProducts) {
        List<CartDTO> carts = new ArrayList<>();

        for (Map.Entry<Cart, List<CartProduct>> item : cartProducts.entrySet()) {
            carts.add(new CartDTO(item.getKey(),
                    item.getValue()
                            .stream()
                            .map(CartProduct::getProduct)
                            .collect(Collectors.toList())));
        }

        return  carts;
    }

}
