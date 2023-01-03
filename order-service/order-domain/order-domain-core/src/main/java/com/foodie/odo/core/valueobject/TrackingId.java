package com.foodie.odo.core.valueobject;

import com.foodie.common.valueobject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID uuid) {
        super(uuid);
    }
}
