package com.foodie.common.entity;

import com.foodie.common.valueobject.Money;
import com.foodie.common.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
    public Product(ProductId productId){
        super.setId(productId);
    }
    String name;
    Money price;
}
