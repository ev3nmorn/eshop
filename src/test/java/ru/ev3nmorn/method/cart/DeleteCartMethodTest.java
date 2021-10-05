package ru.ev3nmorn.method.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ev3nmorn.builder.CartBuilder;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.cart.impl.DeleteCartMethodImpl;
import ru.ev3nmorn.model.Cart;
import ru.ev3nmorn.repository.CartRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DeleteCartMethodTest {

    @Mock
    private CartRepository cartRepository;

    private DeleteCartMethod deleteCartMethod;

    private static final Integer ID = 1;

    @BeforeEach
    public void initMethod() {
        deleteCartMethod = new DeleteCartMethodImpl(cartRepository);
    }

    @Test
    public void cartNotFoundTest() {
        Exception exception = assertThrows(ApiException.class,
                () -> deleteCartMethod.process(ID));

        assertEquals("Cart not found", exception.getMessage());
    }

    @Test
    public void deleteCartTest() {
        when(cartRepository.existsById(ID))
                .thenReturn(true);

        deleteCartMethod.process(ID);

        verify(cartRepository, times(1))
                .deleteById(ID);
    }
}
