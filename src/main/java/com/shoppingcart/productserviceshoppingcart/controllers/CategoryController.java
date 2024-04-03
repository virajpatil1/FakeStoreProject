package com.shoppingcart.productserviceshoppingcart.controllers;

import com.shoppingcart.productserviceshoppingcart.dtos.CategoryRequestDto;
import com.shoppingcart.productserviceshoppingcart.models.Category;
import com.shoppingcart.productserviceshoppingcart.models.Product;
import com.shoppingcart.productserviceshoppingcart.repos.CategoryRepo;
import com.shoppingcart.productserviceshoppingcart.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CategoryController {

    private CategoryRepo categoryRepo;
    private CategoryService categoryService;

    public CategoryController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/categories/{id}")
    public Category categories(@PathVariable("id") Long id) {

        Optional<Category> category = this.categoryRepo.findById(id);

        return category.get();
    }

    @PostMapping("/categories")
    public Category addCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        return categoryService.addCategory(category);
    }

}
