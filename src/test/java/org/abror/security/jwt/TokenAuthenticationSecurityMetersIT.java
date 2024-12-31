// Anvarov Abror
// This file contains integration tests for the TokenAuthenticationSecurityMeters.
// The purpose is to ensure that invalid tokens are properly tracked in the system using a counter in the MeterRegistry.
package org.abror.security.jwt;

import static org.abror.security.jwt.JwtAuthenticationTestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@AuthenticationIntegrationTest
class TokenAuthenticationSecurityMetersIT {

    private static final String INVALID_TOKENS_METER_EXPECTED_NAME = "security.authentication.invalid-tokens";

    @Autowired
    private MockMvc mvc;

    @Value("${jhipster.security.authentication.jwt.base64-secret}")
    private String jwtKey;

    @Autowired
    private MeterRegistry meterRegistry;

    // Ensures that no invalid token counter is incremented when a valid token is used for authentication.
    @Test
    void testValidTokenShouldNotCountAnything() throws Exception {
        Collection<Counter> counters = meterRegistry.find(INVALID_TOKENS_METER_EXPECTED_NAME).counters();

        var count = aggregate(counters);

        tryToAuthenticate(createValidToken(jwtKey));

        assertThat(aggregate(counters)).isEqualTo(count);
    }

    // Verifies that the invalid token counter with the "expired" cause is incremented when an expired token is used.
    @Test
    void testTokenExpiredCount() throws Exception {
        var count = meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "expired").counter().count();

        tryToAuthenticate(createExpiredToken(jwtKey));

        assertThat(meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "expired").counter().count()).isEqualTo(count + 1);
    }

    // Verifies that the counter for invalid tokens with an "invalid-signature" cause is incremented when a token with an invalid signature is used.
    @Test
    void testTokenSignatureInvalidCount() throws Exception {
        var count = meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "invalid-signature").counter().count();

        tryToAuthenticate(createTokenWithDifferentSignature());

        assertThat(meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "invalid-signature").counter().count()).isEqualTo(
            count + 1
        );
    }

    // Checks that the counter for malformed tokens is incremented when a malformed token is used.
    @Test
    void testTokenMalformedCount() throws Exception {
        var count = meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "malformed").counter().count();

        tryToAuthenticate(createSignedInvalidJwt(jwtKey));

        assertThat(meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "malformed").counter().count()).isEqualTo(count + 1);
    }

    // Verifies that the counter for invalid tokens is incremented when an invalid token (not matching expected format) is used.
    @Test
    void testTokenInvalidCount() throws Exception {
        var count = meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "malformed").counter().count();

        tryToAuthenticate(createInvalidToken(jwtKey));

        assertThat(meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "malformed").counter().count()).isEqualTo(count + 1);
    }

    private void tryToAuthenticate(String token) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/authenticate").header(AUTHORIZATION, BEARER + token));
    }

    private double aggregate(Collection<Counter> counters) {
        return counters.stream().mapToDouble(Counter::count).sum();
    }
}
