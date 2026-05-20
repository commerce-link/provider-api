package pl.commercelink.provider.api;

import java.util.Objects;

public sealed interface EventBinding<T> {

    Class<T> eventType();

    record QueueBinding<T>(String queueName, Class<T> eventType) implements EventBinding<T> {}

    record WebhookBinding<T, R>(String path, Class<T> eventType, WebhookExecutor<T, R> executor) implements EventBinding<T> {

        public WebhookBinding {
            Objects.requireNonNull(path, "path");
            Objects.requireNonNull(eventType, "eventType");
            Objects.requireNonNull(executor, "executor");
        }
    }
}
