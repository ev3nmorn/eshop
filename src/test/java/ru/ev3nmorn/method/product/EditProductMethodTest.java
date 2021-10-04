package ru.ev3nmorn.method.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ev3nmorn.builder.ProductBuilder;
import ru.ev3nmorn.dto.ProductDTO;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.product.impl.EditProductMethodImpl;
import ru.ev3nmorn.model.Product;
import ru.ev3nmorn.repository.ProductRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EditProductMethodTest {

    @Mock
    private ProductRepository productRepository;

    private EditProductMethod editProductMethod;

    private static final Integer ID = 1;

    @BeforeEach
    public void initMethod() {
        editProductMethod = new EditProductMethodImpl(productRepository);
    }

    @Test
    public void idNotProvidedTest() {
        Product product = new ProductBuilder()
                .defaultProduct()
                .build();

        assertThrows(ApiException.class, () -> editProductMethod.process(new ProductDTO(product), null),
                "Id is mandatory to update product info");
    }

    @Test
    public void productNotFoundTest() {
        Product product = new ProductBuilder()
                .defaultProduct()
                .build();

        assertThrows(ApiException.class, () -> editProductMethod.process(new ProductDTO(product), ID),
                "Product not found");
    }

    @Test
    public void productAlreadyExistsTest() {
        Product product = new ProductBuilder()
                .defaultProduct()
                .withId(ID)
                .build(),
        sameNamedProduct = new ProductBuilder()
                .defaultProduct()
                .withId(ID + 1)
                .build();


        when(productRepository.findById(ID))
                .thenReturn(Optional.of(product));

        when(productRepository.findByName(product.getName()))
                .thenReturn(sameNamedProduct);

        assertThrows(ApiException.class, () -> editProductMethod.process(new ProductDTO(product), ID),
                "Product with the same name is already exist");
    }

    @Test
    public void saveProductChangesTest() {
        Product product = new ProductBuilder()
                .defaultProduct()
                .withId(ID)
                .build();

        when(productRepository.findById(ID))
                .thenReturn(Optional.of(product));

        editProductMethod.process(new ProductDTO(product), ID);

        verify(productRepository, times(1))
                .save(any());
    }
}
