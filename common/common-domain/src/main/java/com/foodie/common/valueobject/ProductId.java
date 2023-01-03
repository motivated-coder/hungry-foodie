package com.foodie.common.valueobject;

import java.util.UUID;

public class ProductId extends BaseId<UUID> {
    public ProductId(UUID uuid) {
        super(uuid);
    }
}
