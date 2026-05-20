package pl.commercelink.provider.api;

@FunctionalInterface
public interface WebhookExecutor<T, R> {
    R execute(T event, WebhookContext context);
}
