package ru.ev3nmorn.method.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ev3nmorn.builder.ProductBuilder;
import ru.ev3nmorn.dto.mapper.ProductMapper;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.product.impl.GetProductByIdMethodImpl;
import ru.ev3nmorn.model.Product;
import ru.ev3nmorn.repository.ProductRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GetProductByIdMethodTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;

    private GetProductByIdMethod getProductByIdMethod;

    private static final Integer ID = 1;

    @BeforeEach
    public void initMethod() {
        getProductByIdMethod = new GetProductByIdMethodImpl(productRepository, productMapper);
    }

    @Test
    public void productNotFoundTest() {
        assertThrows(ApiException.class, () -> getProductByIdMethod.processToResponse(ID),
                "Product not found");
    }

    @Test
    public void productByIdTest() {
        Product product = new ProductBuilder()
                .defaultProduct()
                .withId(ID)
                .build();

        when(productRepository.findById(ID))
                .thenReturn(Optional.of(product));

        ResponseEntity<?> response = getProductByIdMethod.processToResponse(ID);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
