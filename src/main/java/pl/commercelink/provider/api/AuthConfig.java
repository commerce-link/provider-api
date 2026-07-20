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
            String acceptHeader,
            String refreshTokenFieldKey,
            String contentTypeHeader,
            String deviceAuthUrl
    ) implements AuthConfig {

        public OAuth2(String apiUrl, String authEndpointPath, String refreshEndpointPath,
                      long refreshTokenExpirationSeconds, String acceptHeader) {
            this(apiUrl, authEndpointPath, refreshEndpointPath, refreshTokenExpirationSeconds, acceptHeader, null, null, null);
        }

        public OAuth2(String apiUrl, String authEndpointPath, String refreshEndpointPath,
                      long refreshTokenExpirationSeconds, String acceptHeader, String refreshTokenFieldKey) {
            this(apiUrl, authEndpointPath, refreshEndpointPath, refreshTokenExpirationSeconds, acceptHeader, refreshTokenFieldKey, null, null);
        }

        public OAuth2(String apiUrl, String authEndpointPath, String refreshEndpointPath,
                      long refreshTokenExpirationSeconds, String acceptHeader, String refreshTokenFieldKey,
                      String contentTypeHeader) {
            this(apiUrl, authEndpointPath, refreshEndpointPath, refreshTokenExpirationSeconds, acceptHeader, refreshTokenFieldKey, contentTypeHeader, null);
        }

        public static OAuth2 of(String apiUrl, String authEndpointPath, String refreshEndpointPath,
                                long refreshTokenExpirationSeconds) {
            return new OAuth2(apiUrl, authEndpointPath, refreshEndpointPath, refreshTokenExpirationSeconds, null, null, null, null);
        }
    }
}
