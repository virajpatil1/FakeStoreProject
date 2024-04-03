package com.shoppingcart.productserviceshoppingcart.repos;

import com.shoppingcart.productserviceshoppingcart.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    Optional<Category> findByName(String name);
}
