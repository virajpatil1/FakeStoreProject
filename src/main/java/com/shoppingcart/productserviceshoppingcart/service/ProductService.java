package com.shoppingcart.productserviceshoppingcart.service;

import com.shoppingcart.productserviceshoppingcart.dtos.ProductUpdateRequest;
import com.shoppingcart.productserviceshoppingcart.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    //ResponseEntity<Product> getItemById(Long id);

    List<Product> getAllProducts();
    //ResponseEntity<List<Product>> getAllItems();

    Product deleteProductById(Long id);
    //ResponseEntity<Product>

    Product addProduct(Product product);
    //ResponseEntity<Product> addProduct(Product product);

    Product updateProductById(Long id ,ProductUpdateRequest productUpdateRequest);

    //ResponseEntity<Product> updateItem(Long id, Product product);
}
