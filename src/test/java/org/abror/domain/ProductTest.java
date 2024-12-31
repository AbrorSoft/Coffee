// Anvarov Abror

//This ProductTest class is a unit test that verifies the equality behavior of the Product class.
// It uses AssertJ for assertions and TestUtil to verify the equality contract of the Product class.
package org.abror.domain;

import static org.abror.domain.ProductTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.abror.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Product.class);
        Product product1 = getProductSample1();
        Product product2 = new Product();
        assertThat(product1).isNotEqualTo(product2);

        product2.setId(product1.getId());
        assertThat(product1).isEqualTo(product2);

        product2 = getProductSample2();
        assertThat(product1).isNotEqualTo(product2);
    }
}
