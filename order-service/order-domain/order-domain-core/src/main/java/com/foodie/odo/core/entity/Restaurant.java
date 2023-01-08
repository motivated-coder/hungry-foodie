package com.foodie.odo.core.entity;

import com.foodie.common.entity.AggregateRoot;
import com.foodie.common.entity.Product;
import com.foodie.common.valueobject.RestaurantId;

import java.util.List;

public class Restaurant extends AggregateRoot<RestaurantId> {
    private final List<Product> products;
    private final Boolean active;

    public List<Product> getProducts() {
        return products;
    }

    public Boolean isActive() {
        return active;
    }

    public Restaurant(List<Product> products, Boolean active) {
        this.products = products;
        this.active = active;
    }

    private Restaurant(Builder builder) {
        super.setId(builder.id);
        products = builder.products;
        active = builder.active;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private RestaurantId id;
        private List<Product> products;
        private Boolean active;

        private Builder() {
        }

        public Builder id(RestaurantId val) {
            id = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Builder active(Boolean val) {
            active = val;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }
    }
}
