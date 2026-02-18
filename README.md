# Provider API

Shared interfaces for the CommerceLink provider plugin system.

This module defines the common types that all domain-specific provider APIs (invoicing, shipping, payments, etc.) build on:

- **`ProviderDescriptor<T>`** — base interface that every provider descriptor extends. Declares `name()`, `displayName()`, `configurationFields()`, and `create(Map<String, String>)`.
- **`ProviderField`** — describes a single configuration field (key, label, type, required, placeholder). Field types: `TEXT`, `PASSWORD`, `URL`, `NUMBER`.

## Usage

Domain API modules depend on this artifact and define their own typed descriptor:

```java
public interface XyzProviderDescriptor extends ProviderDescriptor<XyzProvider> {
}
```

Provider implementations then implement that interface and register themselves for `ServiceLoader` discovery by creating a file under `META-INF/services/` whose name is the fully qualified descriptor interface:

```
src/main/resources/META-INF/services/pl.commercelink.xyz.XyzProviderDescriptor
```

with the fully qualified class name of the concrete implementation:

```
pl.commercelink.xyz.myimpl.MyXyzProviderDescriptor
```

Without this registration, the provider will not be discovered at runtime.
