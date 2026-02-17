package com.pickdate.iam.domain


import com.pickdate.bootstrap.exception.IllegalValue
import spock.lang.Specification


class DomainUrlSpec extends Specification {

    def "should throw exception when domain is null"() {
        when:
        DomainUrl.of(null)

        then:
        thrown(IllegalValue)
    }

    def "should throw exception when domain is blank"() {
        when:
        DomainUrl.of("")

        then:
        thrown(IllegalValue)
    }

    def "should trim whitespaces"() {
        expect:
        new DomainUrl("https://example.com  ").value() == "https://example.com"
    }

    def "should accept valid domains"() {
        expect:
        DomainUrl.of(value)

        where:
        value << [
                "https://example.com",
                "http://example.com",
                "https://sub.example-domain.com",
                "http://example.co.uk",
                "http://localhost",
                "http://localhost:8080",
                "https://example.com:443",
                "http://127.0.0.1",
                "http://127.0.0.1:8080",
                "https://255.255.255.255"
        ]
    }

    def "should reject invalid domains"() {
        when:
        DomainUrl.of(value)

        then:
        thrown(IllegalValue)

        where:
        value << [
                "abcdef",
                "www.whatever.com",
                "example.com",
                "ftp://example.com",
                "https://",
                "https://example",
                "https://example.",
                "http://localhost:0",
                "http://localhost:65536",
                "https://this-shouldn't.match@example.com"
        ]
    }
}
