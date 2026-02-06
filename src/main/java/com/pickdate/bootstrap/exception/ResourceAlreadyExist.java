package com.pickdate.bootstrap.exception;

import com.pickdate.bootstrap.domain.Property;
import lombok.Getter;


@Getter
public class ResourceAlreadyExist extends RuntimeException {

    private final Property<Object> property;
    private final String detail;

    public ResourceAlreadyExist(Property<Object> property, String detail) {
        detail = detail == null ? "" : detail;
        this.property = property;
        this.detail = detail;
    }
}
