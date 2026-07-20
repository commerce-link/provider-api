package pl.commercelink.provider.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AuthConfigTest {

    @Test
    void ofLeavesOptionalFieldsNull() {
        // when
        AuthConfig.OAuth2 oauth2 = AuthConfig.OAuth2.of("https://api", "/auth", "/refresh", 100L);

        // then
        assertNull(oauth2.acceptHeader());
        assertNull(oauth2.refreshTokenFieldKey());
    }

    @Test
    void fiveArgConstructorLeavesRefreshTokenFieldKeyNull() {
        // when
        AuthConfig.OAuth2 oauth2 = new AuthConfig.OAuth2(
                "https://api", "/auth", "/refresh", 100L, "application/json");

        // then
        assertEquals("application/json", oauth2.acceptHeader());
        assertNull(oauth2.refreshTokenFieldKey());
    }

    @Test
    void canonicalConstructorCarriesRefreshTokenFieldKey() {
        // when
        AuthConfig.OAuth2 oauth2 = new AuthConfig.OAuth2(
                "https://api", "/auth", "/refresh", 100L, "application/json", "refreshToken");

        // then
        assertEquals("refreshToken", oauth2.refreshTokenFieldKey());
    }

    @Test
    void sixArgConstructorDefaultsContentTypeHeaderToNull() {
        // given / when
        AuthConfig.OAuth2 oauth2 = new AuthConfig.OAuth2(
                "https://api.example.com", "/auth", "/refresh", 100L, "application/vnd.x+json", "refreshToken");

        // then
        assertNull(oauth2.contentTypeHeader());
        assertEquals("refreshToken", oauth2.refreshTokenFieldKey());
    }

    @Test
    void canonicalConstructorCarriesContentTypeHeader() {
        // given / when
        AuthConfig.OAuth2 oauth2 = new AuthConfig.OAuth2(
                "https://api.example.com", "/auth", "/refresh", 100L,
                "application/vnd.x+json", "refreshToken", "application/vnd.x+json");

        // then
        assertEquals("application/vnd.x+json", oauth2.contentTypeHeader());
    }

    @Test
    void sevenArgConstructorDefaultsDeviceAuthUrlToNull() {
        // given / when
        AuthConfig.OAuth2 oauth2 = new AuthConfig.OAuth2(
                "https://api.example.com", "/auth", "/refresh", 100L,
                "application/vnd.x+json", "refreshToken", "application/vnd.x+json");

        // then
        assertNull(oauth2.deviceAuthUrl());
    }

    @Test
    void canonicalConstructorCarriesDeviceAuthUrl() {
        // given / when
        AuthConfig.OAuth2 oauth2 = new AuthConfig.OAuth2(
                "https://api.example.com", "/auth", "/refresh", 100L,
                "application/vnd.x+json", "refreshToken", "application/vnd.x+json",
                "https://auth.example.com/device");

        // then
        assertEquals("https://auth.example.com/device", oauth2.deviceAuthUrl());
    }
}
