package ru.ev3nmorn.method.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.product.impl.DeleteProductMethodImpl;
import ru.ev3nmorn.repository.ProductRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DeleteProductMethodTest {

    @Mock
    private ProductRepository productRepository;

    private DeleteProductMethod deleteProductMethod;

    private static final Integer ID = 1;

    @BeforeEach
    public void initMethod() {
        deleteProductMethod = new DeleteProductMethodImpl(productRepository);
    }

    @Test
    public void productNotFoundTest() {
        when(productRepository.existsById(ID))
                .thenReturn(false);

        Exception exception = assertThrows(ApiException.class,
                () -> deleteProductMethod.process(ID));
        assertEquals("Product not found", exception.getMessage());
    }

    @Test
    public void productDeletedTest() {
        when(productRepository.existsById(ID))
                .thenReturn(true);

        deleteProductMethod.process(ID);

        verify(productRepository, times(1))
                .deleteById(ID);
    }

}
