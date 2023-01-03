package com.foodie.common.valueobject;

import com.foodie.common.valueobject.BaseId;

import java.util.UUID;

public class CustomerId extends BaseId<UUID> {
    public CustomerId(UUID uuid) {
        super(uuid);
    }
}
