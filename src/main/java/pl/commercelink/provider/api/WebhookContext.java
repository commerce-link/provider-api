package pl.commercelink.provider.api;

import java.util.Map;

public record WebhookContext(String storeId, Map<String, String> headers, Map<String, String> providerConfig) {

    public String header(String name) {
        if (headers == null) {
            return null;
        }
        String value = headers.get(name);
        if (value != null) {
            return value;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(name)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
