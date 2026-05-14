package pl.commercelink.provider.api;

import java.util.List;
import java.util.Map;

public interface ProviderDescriptor<T> {

    String name();

    String displayName();

    List<ProviderField> configurationFields();

    T create(Map<String, String> configuration);

    default T create(Map<String, String> configuration, Map<String, Object> context) {
        return create(configuration);
    }

    default Map<String, String> metadata() {
        return Map.of();
    }

    default AuthConfig authConfig() {
        return AuthConfig.None.INSTANCE;
    }

    default List<EventBinding<?>> bindings() {
        return List.of();
    }
}
