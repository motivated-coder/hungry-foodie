package com.foodie.odo.core.valueobject;

import com.foodie.common.valueobject.BaseId;

import java.util.UUID;

public class OrderId extends BaseId<UUID> {
    public OrderId(UUID uuid) {
        super(uuid);
    }
}
