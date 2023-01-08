package com.foodie.common.entity;

import com.foodie.common.valueobject.Money;
import com.foodie.common.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
    public Product(ProductId productId){
        super.setId(productId);
    }
    String name;
    Money price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
