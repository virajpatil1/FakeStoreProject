package com.shoppingcart.productserviceshoppingcart.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Product extends BaseModel implements java.io.Serializable{
    private String title;
    private String description;
    private Long price;
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
    @Transient
    private String categoryName;
    private Long quantity;

}
