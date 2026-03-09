package com.pickdate.ops.audit.infrastructure

import com.pickdate.bootstrap.web.RequestDetails
import com.pickdate.ops.audit.application.AuditEventUseCase
import com.pickdate.ops.audit.domain.AuditEvent
import com.pickdate.ops.audit.domain.LoginFailedEvent
import com.pickdate.ops.audit.domain.LoginSuccessEvent
import com.pickdate.test.fixture.AuthenticationFixture
import spock.lang.Specification


class AuditListenerSpec extends Specification {

    def auditEventUseCase = Mock(AuditEventUseCase)
    def auditListener = new AuditListener(auditEventUseCase)

    def "should save login success event with request details"() {
        given:
        def event = AuthenticationFixture.loginSuccess("john", new RequestDetails("127.0.0.1", "Firefox"))

        when:
        auditListener.onLoginSuccess(event)

        then:
        1 * auditEventUseCase.save({ LoginSuccessEvent auditEvent ->
            auditEvent.userId() == "john" &&
                    auditEvent.remoteAddress() == "127.0.0.1" &&
                    auditEvent.userAgent() == "Firefox" &&
                    auditEvent.action().value == "login_success_event"
        })
    }

    def "should save login failure event with request details"() {
        given:
        def event = AuthenticationFixture.loginFailure("john", new RequestDetails("10.0.0.5", "Chrome"))

        when:
        auditListener.onLoginFailure(event)

        then:
        1 * auditEventUseCase.save({ LoginFailedEvent auditEvent ->
            auditEvent.userId() == "john" &&
                    auditEvent.remoteAddress() == "10.0.0.5" &&
                    auditEvent.userAgent() == "Chrome" &&
                    auditEvent.action().value == "login_failed_event"
        })
    }

    def "should save login success event with null ip and user agent when details are missing"() {
        given:
        def event = AuthenticationFixture.loginSuccess("john")

        when:
        auditListener.onLoginSuccess(event)

        then:
        1 * auditEventUseCase.save({ LoginSuccessEvent auditEvent ->
            auditEvent.userId() == "john" &&
                    auditEvent.remoteAddress() == null &&
                    auditEvent.userAgent() == null
        })
    }

    def "should save login failure event with null ip and user agent when details are missing"() {
        given:
        def event = AuthenticationFixture.loginFailure("john")

        when:
        auditListener.onLoginFailure(event)

        then:
        1 * auditEventUseCase.save({ LoginFailedEvent auditEvent ->
            auditEvent.userId() == "john" &&
                    auditEvent.remoteAddress() == null &&
                    auditEvent.userAgent() == null
        })
    }

    def "should pass through audit event without wrapping"() {
        given:
        def auditEvent = Mock(AuditEvent)

        when:
        auditListener.onAuditLog(auditEvent)

        then:
        1 * auditEventUseCase.save(auditEvent)
    }
}
