package com.foodie.common.entity;

import com.foodie.common.valueobject.Money;
import com.foodie.common.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
    public Product(ProductId productId){
        super.setId(productId);
    }
    String name;
    Money price;

    private Product(Builder builder) {
        id = builder.id;
        setName(builder.name);
        setPrice(builder.price);
    }

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

    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder {
        private ProductId id;
        private String name;
        private Money price;

        private Builder() {
        }



        public Builder id(ProductId val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
