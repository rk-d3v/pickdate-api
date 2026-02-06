package com.pickdate.bootstrap.exception;

import com.pickdate.bootstrap.domain.Property;
import lombok.Getter;

import java.util.List;


@Getter
public class FailedDependency extends RuntimeException {

    private final List<Property<?>> properties;
    private final String detail;

    public FailedDependency(List<Property<?>> properties, String detail) {
        detail = detail == null ? "" : detail;
        this.properties = properties;
        this.detail = detail;
    }

    public FailedDependency(Property<?> property, String detail) {
        this(List.of(property), detail);
    }
}
