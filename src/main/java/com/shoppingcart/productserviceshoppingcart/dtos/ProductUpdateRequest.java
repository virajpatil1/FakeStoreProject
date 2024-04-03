package com.shoppingcart.productserviceshoppingcart.dtos;

import com.shoppingcart.productserviceshoppingcart.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class ProductUpdateRequest {

        private Optional<String> title = Optional.empty();
        private Optional<String> description = Optional.empty();
        private Optional<Long> price = Optional.empty();
        private Optional<Long> quantity = Optional.empty();


//        public static ProductUpdateRequest fromProduct(Product product) {
//                ProductUpdateRequest request = new ProductUpdateRequest();
//                request.setTitle(product.getTitle());
//                request.setDescription(product.getDescription());
//                request.setPrice(product.getPrice());
//                request.setQuantity(product.getQuantity());
//                return request;
//        }

}
