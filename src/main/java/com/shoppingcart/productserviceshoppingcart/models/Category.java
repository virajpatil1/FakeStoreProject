package com.shoppingcart.productserviceshoppingcart.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel implements java.io.Serializable{
    private String name;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER ,mappedBy = "category")
    private List<Product> products;
}
/*
The @JsonManagedReference annotation is the forward part of the relationship – the one that gets serialized normally. The @JsonBackReference annotation is the back part of the relationship – it will be omitted from serialization to avoid the infinite loop.
 */