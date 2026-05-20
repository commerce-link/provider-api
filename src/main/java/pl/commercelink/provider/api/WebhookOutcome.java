package pl.commercelink.provider.api;

public record WebhookOutcome<R>(R result, Object responseBody) {

    public static <R> WebhookOutcome<R> of(R result, Object responseBody) {
        return new WebhookOutcome<>(result, responseBody);
    }

    public static <R> WebhookOutcome<R> empty() {
        return new WebhookOutcome<>(null, null);
    }
}
