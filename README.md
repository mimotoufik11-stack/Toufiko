# DammajAgent (DAMMAJ)

An Android-based agent application with multi-module architecture supporting Android automation, desktop client, and shared core functionality.

## Project Structure

This project is organized into multiple Gradle modules to promote code reuse, separation of concerns, and independent development:

### Modules

#### `app-android`
The main Android application module that serves as the entry point for the mobile application.

**Purpose:**
- Main Android UI and user-facing features
- Integration of automation capabilities
- Application lifecycle management with Hilt dependency injection

**Key Technologies:**
- Android SDK (Min: 24, Target: 34)
- Jetpack libraries: AppCompat, RecyclerView, Lifecycle, WorkManager
- Hilt for dependency injection
- Kotlin Coroutines for asynchronous operations
- ViewBinding for type-safe view access

**Package Structure:**
- `com.dammaj.agent` - Application class and core components
- `com.dammaj.agent.ui` - UI components (Activities, Fragments)

#### `core-shared`
Pure Kotlin/JVM module containing shared business logic and utilities used across all modules.

**Purpose:**
- Common domain models and business logic
- Utility functions and helpers
- Platform-agnostic code that can be shared between Android and Desktop

**Key Technologies:**
- Pure Kotlin/JVM
- Kotlin Coroutines
- No Android dependencies

**Package Structure:**
- `com.dammaj.core.domain` - Domain models and business logic
- `com.dammaj.core.util` - Common utilities

#### `automation-android`
Android library module providing automation capabilities and agent features.

**Purpose:**
- Automation engine implementation
- Task execution framework
- Android-specific automation features

**Key Technologies:**
- Android SDK
- Kotlin Coroutines
- Depends on core-shared module

**Package Structure:**
- `com.dammaj.automation` - Automation engine and related components

#### `desktop-client`
Java/Kotlin desktop application for managing and monitoring the agent.

**Purpose:**
- Desktop control interface for the agent
- Monitoring and configuration management
- Cross-platform desktop support via Swing

**Key Technologies:**
- Pure Kotlin/JVM
- Java Swing for UI
- Kotlin Coroutines
- Depends on core-shared module

**Package Structure:**
- `com.dammaj.desktop` - Desktop application main entry point and UI

## Build Configuration

### Version Catalog
The project uses Gradle Version Catalog (`gradle/libs.versions.toml`) for centralized dependency management. This ensures consistency across all modules and makes version updates easier.

### Code Quality Tools

#### Spotless
Automated code formatting using ktlint:
```bash
./gradlew spotlessCheck    # Check formatting
./gradlew spotlessApply    # Apply formatting
```

#### Detekt
Static code analysis for Kotlin:
```bash
./gradlew detekt           # Run static analysis
```

Configuration: `config/detekt/detekt.yml`

#### Android Lint
Android-specific linting:
```bash
./gradlew lint             # Run lint checks
```

Configuration: `config/lint/lint.xml`

## Build Commands

### Prerequisites
- JDK 17 or higher
- Android SDK with API 34
- Gradle 8.2+ (or use the included wrapper)

### Build All Modules
```bash
./gradlew build
```

### Build Specific Modules

#### Android App
```bash
./gradlew :app-android:build
./gradlew :app-android:assembleDebug       # Debug APK
./gradlew :app-android:assembleRelease     # Release APK
```

#### Core Shared Library
```bash
./gradlew :core-shared:build
```

#### Automation Library
```bash
./gradlew :automation-android:build
```

#### Desktop Client
```bash
./gradlew :desktop-client:build
./gradlew :desktop-client:run              # Run desktop app
./gradlew :desktop-client:jar              # Build executable JAR
```

### Testing

#### Run All Tests
```bash
./gradlew test
```

#### Run Module-Specific Tests
```bash
./gradlew :app-android:testDebugUnitTest
./gradlew :core-shared:test
./gradlew :automation-android:test
./gradlew :desktop-client:test
```

#### Run Android Instrumented Tests
```bash
./gradlew :app-android:connectedAndroidTest
./gradlew :automation-android:connectedAndroidTest
```

### Clean Build
```bash
./gradlew clean
```

## Development Setup

### Android Studio
1. Open the project root directory in Android Studio
2. Wait for Gradle sync to complete
3. Select the `app-android` module in the run configuration
4. Run or debug the application

### IntelliJ IDEA
1. Open the project root directory in IntelliJ IDEA
2. Wait for Gradle sync to complete
3. For Android development, ensure Android plugin is installed
4. For desktop client, select the `desktop-client:run` configuration

## SDK Configuration

- **Minimum SDK:** API 24 (Android 7.0)
- **Target SDK:** API 34 (Android 14)
- **Compile SDK:** API 34
- **Java Version:** 17
- **Kotlin Version:** 1.9.20

## Key Dependencies

### Android
- AndroidX Core KTX
- AppCompat
- Material Components
- RecyclerView
- ConstraintLayout
- Lifecycle (ViewModel, LiveData, Runtime)
- WorkManager
- Hilt (Dependency Injection)

### Kotlin
- Kotlin Standard Library
- Kotlin Coroutines (Core & Android)
- Kotlin Reflection

### Testing
- JUnit 4
- AndroidX Test Extensions
- Espresso
- MockK

## Project Features

### Dependency Injection
The project uses Hilt for dependency injection in Android modules. The main application class is annotated with `@HiltAndroidApp`, and activities/fragments use `@AndroidEntryPoint`.

### Asynchronous Operations
Kotlin Coroutines are used throughout the project for asynchronous operations, providing a clean and efficient way to handle background tasks.

### Code Quality
- Automated code formatting with Spotless
- Static analysis with Detekt
- Android Lint integration
- Consistent code style enforced across all modules

## Contributing

### Code Style
- Follow Kotlin coding conventions
- Use 4 spaces for indentation
- Maximum line length: 120 characters
- Run `./gradlew spotlessApply` before committing

### Before Committing
```bash
./gradlew spotlessApply    # Format code
./gradlew detekt           # Run static analysis
./gradlew test             # Run tests
./gradlew build            # Build all modules
```

## License

Repository code: DAMMAJ
