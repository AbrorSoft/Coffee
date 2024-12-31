// Anvarov Abror

// This file tests the behavior of the JWT authentication system
// by sending HTTP requests to the /api/authenticate endpoint using different tokens and verifying the responses.
package org.abror.security.jwt;

import static org.abror.security.jwt.JwtAuthenticationTestUtils.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@AuthenticationIntegrationTest
class TokenAuthenticationIT {

    @Autowired
    private MockMvc mvc;

    @Value("${jhipster.security.authentication.jwt.base64-secret}")
    private String jwtKey;

    // Tests that a valid JWT token results in a successful authentication (200 OK response).
    @Test
    void testLoginWithValidToken() throws Exception {
        expectOk(createValidToken(jwtKey));
    }

    //  Tests that a token with an invalid signature is rejected with an Unauthorized (401) response.
    @Test
    void testReturnFalseWhenJWThasInvalidSignature() throws Exception {
        expectUnauthorized(createTokenWithDifferentSignature());
    }

    // Tests that a malformed JWT results in an Unauthorized (401) response.
    @Test
    void testReturnFalseWhenJWTisMalformed() throws Exception {
        expectUnauthorized(createSignedInvalidJwt(jwtKey));
    }

    // Tests that an expired JWT token is rejected with an Unauthorized (401) response.
    @Test
    void testReturnFalseWhenJWTisExpired() throws Exception {
        expectUnauthorized(createExpiredToken(jwtKey));
    }

    private void expectOk(String token) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/authenticate").header(AUTHORIZATION, BEARER + token)).andExpect(status().isOk());
    }

    private void expectUnauthorized(String token) throws Exception {
        mvc
            .perform(MockMvcRequestBuilders.get("/api/authenticate").header(AUTHORIZATION, BEARER + token))
            .andExpect(status().isUnauthorized());
    }
}
