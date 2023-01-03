package com.foodie.common.entity;

import com.foodie.common.valueobject.Money;
import com.foodie.common.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
    String name;
    Money price;
}
