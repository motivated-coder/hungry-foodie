package com.foodie.common.valueobject;

import java.util.UUID;

public class RestaurantId extends BaseId<UUID>{
    public RestaurantId(UUID uuid) {
        super(uuid);
    }
}
