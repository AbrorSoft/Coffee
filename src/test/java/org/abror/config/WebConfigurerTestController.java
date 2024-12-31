//Anvarov Abror

// The WebConfigurerTestController class you've provided is a simple Spring REST controller designed to test Cross-Origin Resource Sharing (CORS) configuration in a Spring Boot application.
package org.abror.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebConfigurerTestController {

    @GetMapping("/api/test-cors")
    public void testCorsOnApiPath() {}

    @GetMapping("/test/test-cors")
    public void testCorsOnOtherPath() {}
}
