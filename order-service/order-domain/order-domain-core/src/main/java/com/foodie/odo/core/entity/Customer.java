package com.foodie.odo.core.entity;

import com.foodie.common.entity.AggregateRoot;
import com.foodie.common.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

    private Customer(Builder builder) {
        super.setId(builder.id);
    }

    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder {
        private CustomerId id;

        private Builder() {
        }



        public Builder id(CustomerId val) {
            id = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
