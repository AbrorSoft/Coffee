// Anvarov Abror
// This Java class, ProductTestSamples,
// is designed to generate sample Product objects, primarily for testing purposes.
package org.abror.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ProductTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Product getProductSample1() {
        return new Product().id(1L).name("name1").description("description1").imageKey("imageKey1").gram("gram1").calories("calories1");
    }

    public static Product getProductSample2() {
        return new Product().id(2L).name("name2").description("description2").imageKey("imageKey2").gram("gram2").calories("calories2");
    }

    public static Product getProductRandomSampleGenerator() {
        return new Product()
            .id(longCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .imageKey(UUID.randomUUID().toString())
            .gram(UUID.randomUUID().toString())
            .calories(UUID.randomUUID().toString());
    }
}
