package com.foodie.odo.core.valueobject;

import com.foodie.common.valueobject.BaseId;

import java.util.UUID;

public class StreetAddress extends BaseId<UUID> {
    public StreetAddress(UUID uuid, String street, String postalCode, String city) {
        super(uuid);
        this.street=street;
        this.postalCode=postalCode;
        this.city=city;
    }
    private final String street;
    private final String postalCode;
    private final String city;

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }
}
