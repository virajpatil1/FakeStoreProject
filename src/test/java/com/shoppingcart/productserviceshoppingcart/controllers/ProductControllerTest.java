package com.shoppingcart.productserviceshoppingcart.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import com.shoppingcart.productserviceshoppingcart.exceptions.ProductNotFoundException;
import com.shoppingcart.productserviceshoppingcart.models.Product;
import com.shoppingcart.productserviceshoppingcart.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;


public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    //postive test
    @Test
    public void getProductByIdReturnsProductWhenProductExists() {
        //assign
        Long id = 1L;
        Product product = new Product();
        product.setId(id);

        //act
        when(productService.getProductById(id)).thenReturn(product);

        Product result = productController.getProductbyId(id);
        System.out.println(result.getId());

        //assert
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    //negative test
    @Test
    public void getProductByIdThrowsExceptionWhenProductDoesNotExist() {
        Long id = 1L;
        when(productService.getProductById(id)).thenThrow(new ProductNotFoundException("Product not found"));

        assertThrows(ProductNotFoundException.class, () -> productController.getProductbyId(id));
    }
}
