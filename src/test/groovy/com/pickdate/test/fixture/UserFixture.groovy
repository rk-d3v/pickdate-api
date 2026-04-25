package com.pickdate.test.fixture

import com.pickdate.iam.domain.Authority
import com.pickdate.iam.domain.Password
import com.pickdate.iam.domain.User
import com.pickdate.shared.domain.Email

class UserFixture {

    static User SOME_ADMIN = new User()
            .withEmail(Email.of("admin@email.com"))
            .withPassword(Password.fromPlaintext("superSecretPass!"))
            .addAuthority(Authority.ADMIN)

    static User SOME_USER = new User()
            .withEmail(Email.of("user@email.com"))
            .withPassword(Password.fromPlaintext("superSecretPass!"))
            .addAuthority(Authority.USER)
}
