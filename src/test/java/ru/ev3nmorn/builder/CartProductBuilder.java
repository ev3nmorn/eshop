package ru.ev3nmorn.builder;

import ru.ev3nmorn.model.Cart;
import ru.ev3nmorn.model.CartProduct;
import ru.ev3nmorn.model.Product;

public class CartProductBuilder {

    private CartProduct cartProduct;

    public CartProductBuilder() {
        cartProduct = new CartProduct();
    }

    public CartProductBuilder defaultCartProduct() {
        cartProduct.setId(1);
        cartProduct.setProduct(new ProductBuilder()
                .defaultProduct()
                .build());
        cartProduct.setCart(new CartBuilder()
                .defaultCart()
                .build());

        return this;
    }

    public CartProductBuilder withId(Integer id) {
        cartProduct.setId(id);

        return this;
    }

    public CartProductBuilder withCart(Cart cart) {
        cartProduct.setCart(cart);

        return this;
    }

    public CartProductBuilder withProduct(Product product) {
        cartProduct.setProduct(product);

        return this;
    }

    public CartProduct build() {
        return cartProduct;
    }
}
