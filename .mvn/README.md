# Develocity Build Cache Integration

This directory contains configuration files for the Develocity (formerly Gradle Enterprise) build cache integration with Maven.

## Configuration Files

- `extensions.xml`: Configures the Gradle Enterprise Maven extension
- `gradle-enterprise.xml`: Configures the build cache settings
- `jvm.config`: Sets JVM arguments for Maven
- `maven.config`: Configures Maven settings

## Benefits

- Faster builds through local and remote build caching
- Build scans for better build insights
- Improved CI/CD performance

## Usage

No special commands are needed. The build cache is automatically used when running Maven commands:

```bash
mvn clean install
```

The first build will populate the cache, and subsequent builds will use the cache for faster build times.
