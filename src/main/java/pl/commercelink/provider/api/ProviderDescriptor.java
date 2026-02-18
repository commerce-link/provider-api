package pl.commercelink.provider.api;

import java.util.List;
import java.util.Map;

public interface ProviderDescriptor<T> {

    String name();

    String displayName();

    List<ProviderField> configurationFields();

    T create(Map<String, String> configuration);
}
