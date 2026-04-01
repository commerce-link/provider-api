package pl.commercelink.provider.api;

public sealed interface EventBinding<T> {

    Class<T> eventType();

    record QueueBinding<T>(String queueName, Class<T> eventType) implements EventBinding<T> {}

    record WebhookBinding<T>(String path, Class<T> eventType) implements EventBinding<T> {}
}
