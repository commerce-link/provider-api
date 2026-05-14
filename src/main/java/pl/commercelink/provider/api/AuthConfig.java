package pl.commercelink.provider.api;

public sealed interface AuthConfig permits AuthConfig.None, AuthConfig.OAuth2 {

    enum None implements AuthConfig {
        INSTANCE
    }

    record OAuth2(
            String apiUrl,
            String authEndpointPath,
            String refreshEndpointPath,
            long refreshTokenExpirationSeconds,
            String acceptHeader
    ) implements AuthConfig {

        public static OAuth2 of(
                String apiUrl,
                String authEndpointPath,
                String refreshEndpointPath,
                long refreshTokenExpirationSeconds) {
            return new OAuth2(apiUrl, authEndpointPath, refreshEndpointPath, refreshTokenExpirationSeconds, null);
        }
    }
}
