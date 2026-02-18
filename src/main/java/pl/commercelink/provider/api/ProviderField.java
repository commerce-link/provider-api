package pl.commercelink.provider.api;

public record ProviderField(
    String key,
    String label,
    FieldType type,
    boolean required,
    String placeholder
) {
    public enum FieldType { TEXT, PASSWORD, URL, NUMBER }
}
