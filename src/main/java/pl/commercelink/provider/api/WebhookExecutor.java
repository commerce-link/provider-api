package pl.commercelink.provider.api;

@FunctionalInterface
public interface WebhookExecutor<R> {
    WebhookOutcome<R> execute(String payload, WebhookContext context);
}
