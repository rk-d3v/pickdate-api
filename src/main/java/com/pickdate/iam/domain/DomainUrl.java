package com.pickdate.iam.domain;

import com.pickdate.bootstrap.domain.Value;
import com.pickdate.bootstrap.validation.Assert;
import jakarta.annotation.Nonnull;

import java.util.regex.Pattern;

import static com.pickdate.bootstrap.validation.Matchers.isValidPort;
import static com.pickdate.bootstrap.validation.Matchers.oneRegexOf;
import static java.util.regex.Pattern.compile;


public record DomainUrl(String value) implements Value<String> {

    private static final int MAX_LENGTH = 255;

    private static final Pattern LOCALHOST_PATTERN = compile(
            "^(https?://)localhost(?::\\d{1,5})?$"
    );

    private static final Pattern IPV4_PATTERN = compile(
            "^(https?://)(\\b25[0-5]|\\b2[0-4][0-9]|\\b[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}(?::\\d{1,5})?$"
    );

    private static final Pattern DOMAIN_NAME_PATTERN = compile(
            "https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()!@:%_+.~#?&/=]*)"
    );


    public DomainUrl(String value) {
        this.value = value == null ? null : value.strip();
        validate(this.value);
    }

    public static DomainUrl of(String value) {
        return new DomainUrl(value);
    }

    public static void validate(String value) {
        Assert.that("domain", value)
                .isNotBlank("Domain cannot be null or blank")
                .hasMaxLength(MAX_LENGTH, "Domain must be at most " + MAX_LENGTH + " characters long")
                .matches(oneRegexOf(
                        LOCALHOST_PATTERN,
                        IPV4_PATTERN,
                        DOMAIN_NAME_PATTERN
                ), "Invalid domain format; expected http(s)://<host>[:port]");

        if (containsPort(value)) {
            Assert.that("domain", value)
                    .matches(isValidPort(), "Invalid port number");
        }
    }

    private static boolean containsPort(String value) {
        return value.contains(":") &&
                value.indexOf(':') < value.lastIndexOf(':');
    }

    @Override
    public @Nonnull String toString() {
        return value == null ? "null" : value;
    }
}
