package com.shoppingcart.productserviceshoppingcart.controllers;
import com.shoppingcart.productserviceshoppingcart.dtos.ProductUpdateRequest;
import com.shoppingcart.productserviceshoppingcart.exceptions.ProductNotFoundException;
import com.shoppingcart.productserviceshoppingcart.models.Category;
import com.shoppingcart.productserviceshoppingcart.models.Product;
import com.shoppingcart.productserviceshoppingcart.service.CategoryService;
import com.shoppingcart.productserviceshoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    private CategoryService categoryService;

    @Autowired
    public ProductController(@Qualifier("FakeProductService") ProductService productService , CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public Product getProductbyId(@PathVariable("id") Long id) throws ProductNotFoundException{

        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return  productService.getAllProducts();
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){


        return productService.addProduct(product);

    }

    @DeleteMapping("/{id}")
    public Product deleteProductById(@PathVariable("id") Long id){
        return productService.deleteProductById(id);
    }


    @PutMapping("/{id}")
    public Product updateProductById(@PathVariable("id") Long id , @RequestBody ProductUpdateRequest productUpdateRequest){



        return productService.updateProductById(id , productUpdateRequest);
    }




}
