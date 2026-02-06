package com.pickdate.iam.domain;

import com.pickdate.bootstrap.domain.Property;
import com.pickdate.bootstrap.exception.NotFound;

public class UserNotFound extends NotFound {

    static final String MESSAGE = "User not found";

    public UserNotFound(String username) {
        this(Property.of("username", username), MESSAGE);
    }

    public UserNotFound(Property<Object> property, String message) {
        super(property, message);
    }

    public static UserNotFound withUsername(String username) {
        return new UserNotFound(username);
    }
}
