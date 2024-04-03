package com.shoppingcart.productserviceshoppingcart.service;

import com.shoppingcart.productserviceshoppingcart.dtos.ProductUpdateRequest;
import com.shoppingcart.productserviceshoppingcart.exceptions.ProductNotFoundException;
import com.shoppingcart.productserviceshoppingcart.models.Category;
import com.shoppingcart.productserviceshoppingcart.models.Product;
import com.shoppingcart.productserviceshoppingcart.repos.CategoryRepo;
import com.shoppingcart.productserviceshoppingcart.repos.ProductRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;


@Service("SelfProductService")
public class ProductServiceImpl implements ProductService{

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;



    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;

    }

    @Override
    public Product getProductById(Long id) {
//
//        ResponseEntity<Optional> responseEntity = this.restTemplateConfig.restTemplate()
//                .getForEntity("http://user-service/" + id, Optional.class);

        Optional<Product> product = this.productRepo.findById(id);
        if(product.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        Product productResponse = product.get();
        Category category = productResponse.getCategory();
        if (category != null) {
            productResponse.setCategoryName(category.getName());
        }

        return productResponse;
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = this.productRepo.findAll();

        products.forEach(product -> {
            if (product.getCategory() != null) {
                product.setCategoryName(product.getCategory().getName());
            }
        });

        return products;
    }

    @Override
    public Product deleteProductById(Long id) {
        Optional<Product> product = this.productRepo.findById(id);

        if(product.isEmpty()){
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        this.productRepo.deleteById(id);
        Product productResponse = product.get();
        Category category = productResponse.getCategory();
        if (category != null) {
            productResponse.setCategoryName(category.getName());
        }

        return productResponse;
    }

    @Override
    public Product addProduct(Product product) {

        Optional<Category> categoryOptional =
                this.categoryRepo.findByName(product.getCategory().getName());

        if(categoryOptional.isPresent()){
            product.setCategory(categoryOptional.get());
        } else {
            Category category = categoryRepo.save(product.getCategory());
            product.setCategory(category);
        }


        Product response =  this.productRepo.save(product);
        Category category = response.getCategory();

        if (category != null) {
            response.setCategoryName(category.getName());
        }

        return response;


    }

    @Override
    public Product updateProductById(Long id ,ProductUpdateRequest  updateRequest ) {

        Optional<Product> productOptional = productRepo.findById(id);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        Product existingProduct = productOptional.get();

        updateRequest.getTitle().ifPresent(existingProduct::setTitle);
        updateRequest.getDescription().ifPresent(existingProduct::setDescription);
        updateRequest.getPrice().ifPresent(existingProduct::setPrice);
        updateRequest.getQuantity().ifPresent(existingProduct::setQuantity);
        Product updatedProduct = productRepo.save(existingProduct);

        Category category = updatedProduct.getCategory();

        if (category != null) {
            updatedProduct.setCategoryName(category.getName());
        }

        return updatedProduct;


    }
}
