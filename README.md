# provider-api

Base plugin system for CommerceLink service providers. Defines the `ProviderDescriptor<T>` contract that all provider integrations implement — invoicing, shipping, payments, PIM, and others.

Implementations are discovered at runtime via Java `ServiceLoader` (`META-INF/services/`).

## ProviderDescriptor<T>

| Method                  | Required | Description                                                                 |
|-------------------------|----------|-----------------------------------------------------------------------------|
| `name()`                | yes      | Unique identifier, e.g. `"fakturownia"`, `"stripe"`, `"commercelink"`      |
| `displayName()`         | yes      | Human-readable name, e.g. `"Fakturownia"`, `"Stripe"`, `"CommerceLink PIM"`|
| `configurationFields()` | yes      | List of `ProviderField` entries describing the config schema                |
| `create(configuration)` | yes      | Creates provider instance from `Map<String, String>` configuration          |
| `create(configuration, context)` | default | Creates provider with additional runtime objects (e.g. `SqsAsyncClient`). Falls back to `create(configuration)` |
| `metadata()`            | default  | Provider metadata, e.g. `{"authType": "oauth2"}`. Empty by default         |
| `bindings()`            | default  | Event bindings the provider needs (SQS queues, webhooks). Empty by default  |

## ProviderField

Describes a single configuration parameter:

```java
new ProviderField("apiKey", "API Key", FieldType.PASSWORD, true, "sk_live_...")
```

| Field         | Description                                           |
|---------------|-------------------------------------------------------|
| `key`         | Configuration map key                                 |
| `label`       | Human-readable label for UI                           |
| `type`        | `TEXT`, `PASSWORD`, `URL`, or `NUMBER`                 |
| `required`    | Whether the field is mandatory                        |
| `placeholder` | Placeholder/example value for UI                      |

## EventBinding

Sealed interface for declaring event sources. The hosting application reads `bindings()` and sets up the appropriate infrastructure.

| Type              | Fields              | Description                        |
|-------------------|---------------------|------------------------------------|
| `QueueBinding<T>` | `queueName`, `eventType` | SQS queue to listen on        |
| `WebhookBinding<T>` | `path`, `eventType`   | HTTP webhook endpoint to register |

The application pattern-matches on the binding type:

```java
for (EventBinding<?> binding : descriptor.bindings()) {
    switch (binding) {
        case QueueBinding<?> q -> // set up SQS listener for q.queueName()
        case WebhookBinding<?> w -> // set up HTTP route for w.path()
    }
}
```

## Existing provider APIs

| API                          | Descriptor interface              | Implementations                         |
|------------------------------|-----------------------------------|-----------------------------------------|
| `invoicing-api`              | `InvoicingProviderDescriptor`     | `invoicing-fakturownia`, `invoicing-saldeosmart` |
| `shipping-api`               | `ShippingProviderDescriptor`      | `shipping-furgonetka`                   |
| `payments-api`               | `PaymentProviderDescriptor`       | `payments-stripe`, `payments-paynow`    |
| `pim-api`                    | `PimCatalogDescriptor`            | `pim-commercelink`                      |
| `marketplace-api`            | `MarketplaceDescriptor`           | `marketplace-morele`, `marketplace-empik`|

## Creating a new provider

1. Define a domain-specific API module (e.g. `my-api`) with an interface extending `ProviderDescriptor<T>`
2. Create an implementation module (e.g. `my-provider`)
3. Implement the descriptor with `name()`, `displayName()`, `configurationFields()`, `create()`
4. Register via `META-INF/services/<descriptor-interface-fqn>`
5. Add the implementation module as a dependency in `app/pom.xml`

## License

MIT
