package com.pickdate.bootstrap.domain;

import java.io.Serializable;


public interface Value<T> extends Serializable {

    static <T> T valueOf(Value<T> object) {
        return object == null ? null : object.value();
    }

    T value();
}
