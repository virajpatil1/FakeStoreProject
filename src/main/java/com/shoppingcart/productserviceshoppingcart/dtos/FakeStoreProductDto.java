package com.shoppingcart.productserviceshoppingcart.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private Long price;
    private String category;
}
