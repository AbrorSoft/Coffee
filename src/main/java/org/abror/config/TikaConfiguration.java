package org.abror.config;

import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Abror
 * @see org.abror.config
 * @since 9/21/2024 7:19 PM
 */
@Configuration
public class TikaConfiguration {

    /**
     *
     * @return
     */
    @Bean
    public Tika tika() {
        return new Tika();
    }
}
