package com.pickdate.iam.domain;

import java.util.List;


public record UserData(
        String username,
        String email,
        List<String> authorities
) {

    public static UserData from(User user) {
        return new UserData(
                user.getUsername().value(),
                user.getEmail().value(),
                user.getAuthoritiesAsString()
        );
    }
}
