package ru.ev3nmorn.method.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ev3nmorn.builder.ProductBuilder;
import ru.ev3nmorn.dto.ProductDTO;
import ru.ev3nmorn.dto.mapper.ProductMapper;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.product.impl.AddProductMethodImpl;
import ru.ev3nmorn.model.Product;
import ru.ev3nmorn.repository.ProductRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AddProductMethodTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;

    private AddProductMethod addProductMethod;

    private static final String EXIST_NAME = "tv";

    @BeforeEach
    public void initMethod() {
        addProductMethod = new AddProductMethodImpl(productRepository, productMapper);
    }

    @Test
    public void productAlreadyExistTest() {
        Product product = new ProductBuilder()
                .defaultProduct()
                .withName(EXIST_NAME)
                .build();

        when(productRepository.findByName(EXIST_NAME))
                .thenReturn(product);

        assertThrows(ApiException.class, () -> addProductMethod.process(new ProductDTO(product)),
                "Product with the same name is already exist");
    }

    @Test
    public void saveProductTest() {
        Product product = new ProductBuilder()
                .defaultProduct()
                .build();

        addProductMethod.process(new ProductDTO(product));

        verify(productRepository, times(1))
                .save(any());
    }
}
