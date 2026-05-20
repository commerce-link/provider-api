package pl.commercelink.provider.api;

import java.util.Objects;

public sealed interface EventBinding<T> {

    Class<T> eventType();

    record QueueBinding<T>(String queueName, Class<T> eventType) implements EventBinding<T> {}

    record WebhookBinding<R>(String path, WebhookExecutor<R> executor) implements EventBinding<String> {

        public WebhookBinding {
            Objects.requireNonNull(path, "path");
            Objects.requireNonNull(executor, "executor");
        }

        @Override
        public Class<String> eventType() {
            return String.class;
        }
    }
}
