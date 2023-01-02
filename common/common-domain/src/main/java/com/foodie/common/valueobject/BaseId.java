package com.foodie.common.valueobject;

import java.util.Objects;

public class BaseId<T> {
    private final T t;

    public BaseId(T t) {
        this.t = t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseId)) return false;
        BaseId<?> baseId = (BaseId<?>) o;
        return Objects.equals(t, baseId.t);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t);
    }
}
