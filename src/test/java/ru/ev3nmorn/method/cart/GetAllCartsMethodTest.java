package ru.ev3nmorn.method.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ev3nmorn.builder.CartBuilder;
import ru.ev3nmorn.dto.mapper.CartMapper;
import ru.ev3nmorn.method.cart.impl.GetAllCartsMethodImpl;
import ru.ev3nmorn.repository.CartProductRepository;
import ru.ev3nmorn.repository.CartRepository;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GetAllCartsMethodTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private CartProductRepository cartProductRepository;
    @Mock
    private CartMapper cartMapper;

    private GetAllCartsMethod getAllCartsMethod;

    @BeforeEach
    public void initMethod() {
        getAllCartsMethod = new GetAllCartsMethodImpl(cartProductRepository, cartRepository, cartMapper);
    }

    @Test
    public void allCartsTest() {
        when(cartRepository.findAll())
                .thenReturn(List.of(new CartBuilder()
                        .defaultCart()
                        .build()));

        ResponseEntity<?> response = getAllCartsMethod.processToResponse();

        verify(cartRepository, times(1))
                .findAll();
        verify(cartProductRepository, times(1))
                .findByCartId(any());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
