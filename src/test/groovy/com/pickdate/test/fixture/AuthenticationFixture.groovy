package com.pickdate.test.fixture

import com.pickdate.bootstrap.web.RequestDetails
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import org.springframework.security.core.Authentication

class AuthenticationFixture {

    static AuthenticationSuccessEvent loginSuccess(String username) {
        new AuthenticationSuccessEvent(authentication(username))
    }

    static AuthenticationSuccessEvent loginSuccess(String username, RequestDetails details) {
        new AuthenticationSuccessEvent(authentication(username, details))
    }

    static AbstractAuthenticationFailureEvent loginFailure(String username) {
        new TestAuthenticationFailureEvent(authentication(username))
    }

    static AbstractAuthenticationFailureEvent loginFailure(String username, RequestDetails details) {
        new TestAuthenticationFailureEvent(authentication(username, details))
    }

    static UsernamePasswordAuthenticationToken authentication(String username, Object details = null) {
        def authentication = new UsernamePasswordAuthenticationToken(username, "secret")
        authentication.details = details
        authentication
    }

    private static final class TestAuthenticationFailureEvent extends AbstractAuthenticationFailureEvent {

        TestAuthenticationFailureEvent(Authentication authentication) {
            super(authentication, new BadCredentialsException("Bad credentials"))
        }
    }
}

