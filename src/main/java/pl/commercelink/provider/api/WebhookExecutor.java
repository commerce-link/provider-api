package pl.commercelink.provider.api;

@FunctionalInterface
public interface WebhookExecutor<R> {
    R execute(String payload, WebhookContext context);
}
