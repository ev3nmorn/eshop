package ru.ev3nmorn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.ev3nmorn.dto.CartDTO;
import ru.ev3nmorn.method.cart.*;
import ru.ev3nmorn.service.CartService;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final GetAllCartsMethod getAllCartsMethod;
    private final GetCustomerCartsMethod getCustomerCartsMethod;
    private final AddCartMethod addCartMethod;
    private final DeleteCartMethod deleteCartMethod;
    private final GetCartByIdMethod getCartByIdMethod;
    private final AddProductToCartMethod addProductToCartMethod;
    private final DeleteProductFromCartMethod deleteProductFromCartMethod;

    @Autowired
    public CartServiceImpl(GetAllCartsMethod getAllCartsMethod, GetCustomerCartsMethod getCustomerCartsMethod,
                           AddCartMethod addCartMethod, DeleteCartMethod deleteCartMethod,
                           GetCartByIdMethod getCartByIdMethod, AddProductToCartMethod addProductToCartMethod,
                           DeleteProductFromCartMethod deleteProductFromCartMethod) {
        this.getAllCartsMethod = getAllCartsMethod;
        this.getCustomerCartsMethod = getCustomerCartsMethod;
        this.addCartMethod = addCartMethod;
        this.deleteCartMethod = deleteCartMethod;
        this.getCartByIdMethod = getCartByIdMethod;
        this.addProductToCartMethod = addProductToCartMethod;
        this.deleteProductFromCartMethod = deleteProductFromCartMethod;
    }

    public ResponseEntity<List<CartDTO>> allCarts() {
        return getAllCartsMethod.processToResponse();
    }

    public ResponseEntity<CartDTO> getCartById(Integer id) {
        return getCartByIdMethod.processToResponse(id);
    }

    public ResponseEntity<List<CartDTO>> getCustomerCarts(Integer id) {
        return getCustomerCartsMethod.processToResponse(id);
    }

    public void addCart(Integer customerId) {
        addCartMethod.process(customerId);
    }

    public void deleteCart(Integer id) {
        deleteCartMethod.process(id);
    }

    public void addProductToCart(Integer id, Integer productId) {
        addProductToCartMethod.process(id, productId);
    }

    public void deleteProductFromCart(Integer cartId, Integer productId) {
        deleteProductFromCartMethod.process(cartId, productId);
    }
}