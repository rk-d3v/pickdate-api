package com.pickdate.iam.infrastructure;


import com.pickdate.bootstrap.domain.Email;
import com.pickdate.bootstrap.domain.Password;
import com.pickdate.bootstrap.domain.Username;
import com.pickdate.iam.domain.User;

record CreateUserRequest(
        String username,
        String password,
        String email
) {

    public User user() {
        return new User(
                Username.of(username),
                Password.fromPlaintext(password),
                Email.of(email)
        );
    }
}
