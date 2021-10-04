package ru.ev3nmorn.method.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ev3nmorn.dto.ProductDTO;
import ru.ev3nmorn.dto.mapper.ProductMapper;
import ru.ev3nmorn.method.product.impl.GetAllProductsMethodImpl;
import ru.ev3nmorn.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GetAllProductsMethodTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;

    private GetAllProductsMethod getAllProductsMethod;

    @BeforeEach
    public void initMethod() {
        getAllProductsMethod = new GetAllProductsMethodImpl(productRepository, productMapper);
    }

    @Test
    public void allProductsTest() {
        when(productRepository.findAll())
                .thenReturn(new ArrayList<>());

        ResponseEntity<List<ProductDTO>> response = getAllProductsMethod.processToResponse();

        verify(productRepository, times(1))
                .findAll();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
