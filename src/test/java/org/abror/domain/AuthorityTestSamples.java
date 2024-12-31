//Anvarov Abror

// The AuthorityTestSamples class generates sample data for the Authority class. It provides several methods for creating Authority
// objects that can be used in unit tests or other testing scenarios.
package org.abror.domain;

import java.util.UUID;

public class AuthorityTestSamples {

    public static Authority getAuthoritySample1() {
        return new Authority().name("name1");
    }

    public static Authority getAuthoritySample2() {
        return new Authority().name("name2");
    }

    public static Authority getAuthorityRandomSampleGenerator() {
        return new Authority().name(UUID.randomUUID().toString());
    }
}
