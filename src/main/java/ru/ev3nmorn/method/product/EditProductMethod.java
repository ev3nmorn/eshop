package ru.ev3nmorn.method.product;

import ru.ev3nmorn.dto.ProductDTO;

public interface EditProductMethod {

    void process(ProductDTO product, Integer id);

}
