// Anvarov Abror

// This file tests the SecurityMetersService class,
// which is responsible for tracking and updating counters related to invalid authentication tokens.
// It ensures that counters are created for different types of token failures and that the counters are updated
// correctly when specific methods are called.
package org.abror.management;

import static org.assertj.core.api.Assertions.assertThat;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SecurityMetersServiceTests {

    private static final String INVALID_TOKENS_METER_EXPECTED_NAME = "security.authentication.invalid-tokens";

    private MeterRegistry meterRegistry;

    private SecurityMetersService securityMetersService;

    @BeforeEach
    public void setup() {
        meterRegistry = new SimpleMeterRegistry();

        securityMetersService = new SecurityMetersService(meterRegistry);
    }

    //  This test verifies that counters for various invalid token causes
    //  (like expired, unsupported, invalid signature, and malformed) are created correctly in the MeterRegistry.
    @Test
    void testInvalidTokensCountersByCauseAreCreated() {
        meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).counter();

        meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "expired").counter();

        meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "unsupported").counter();

        meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "invalid-signature").counter();

        meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "malformed").counter();

        Collection<Counter> counters = meterRegistry.find(INVALID_TOKENS_METER_EXPECTED_NAME).counters();

        assertThat(counters).hasSize(4);
    }

    // This test ensures that when the SecurityMetersService methods (e.g., trackTokenExpired, trackTokenUnsupported, etc.) are called,
    // the appropriate counters in the MeterRegistry are incremented correctly.
    @Test
    void testCountMethodsShouldBeBoundToCorrectCounters() {
        assertThat(meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "expired").counter().count()).isZero();

        securityMetersService.trackTokenExpired();

        assertThat(meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "expired").counter().count()).isEqualTo(1);

        assertThat(meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "unsupported").counter().count()).isZero();

        securityMetersService.trackTokenUnsupported();

        assertThat(meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "unsupported").counter().count()).isEqualTo(1);

        assertThat(meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "invalid-signature").counter().count()).isZero();

        securityMetersService.trackTokenInvalidSignature();

        assertThat(meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "invalid-signature").counter().count()).isEqualTo(1);

        assertThat(meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "malformed").counter().count()).isZero();

        securityMetersService.trackTokenMalformed();

        assertThat(meterRegistry.get(INVALID_TOKENS_METER_EXPECTED_NAME).tag("cause", "malformed").counter().count()).isEqualTo(1);
    }
}
