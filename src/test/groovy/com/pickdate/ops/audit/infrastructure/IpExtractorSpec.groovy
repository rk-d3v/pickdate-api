package com.pickdate.ops.audit.infrastructure

import com.pickdate.bootstrap.web.RequestDetails
import com.pickdate.test.fixture.AuthenticationFixture
import spock.lang.Specification

class IpExtractorSpec extends Specification {

    def "should extract ip from request details"() {
        given:
        def authentication = AuthenticationFixture.authentication("john", new RequestDetails("127.0.0.1", "Firefox"))

        expect:
        IpExtractor.extractIp(authentication) == "127.0.0.1"
    }

    def "should extract user agent from request details"() {
        given:
        def authentication = AuthenticationFixture.authentication("john", new RequestDetails("127.0.0.1", "Firefox"))

        expect:
        IpExtractor.extractUserAgent(authentication) == "Firefox"
    }
}

