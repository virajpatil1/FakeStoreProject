package com.shoppingcart.productserviceshoppingcart.service;

import com.shoppingcart.productserviceshoppingcart.models.Category;
import com.shoppingcart.productserviceshoppingcart.repos.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Category addCategory(Category category) {

      return categoryRepo.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }


}
