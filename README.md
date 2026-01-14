# CryptX - Crypto Wallet App

A Kotlin Multiplatform (KMP) project for a crypto wallet application supporting Android and iOS.

## Architecture Chosen

This project uses a **Layer-based Architecture** with the following layers:
- **Domain**: Business logic and entities
- **Repository**: Data access interfaces
- **State**: UI state management
- **UseCase**: Application use cases
- **ViewModel**: Presentation logic

## Alternative Architectures

### Feature-Based Architecture
Each feature is a module with its own layers (presentation, data, domain).

```
features/
  wallet/
    presentation/
      viewmodels/
      views/
    data/
      repositories/
      models/
    domain/
      usecases/
      entities/
  transaction/
    presentation/
      viewmodels/
      views/
    data/
      repositories/
      models/
    domain/
      usecases/
      entities/
```

### Layer-Based Architecture
Global layers across the app (presentation, data, domain).

```
src/
  presentation/
    viewmodels/
    views/
  data/
    repositories/
    models/
  domain/
    usecases/
    entities/
```

### Hybrid Architecture
Combination: Feature-based for UI features, layer-based for shared logic.

```
features/
  wallet/
    presentation/
      viewmodels/
      views/
shared/
  data/
    repositories/
    models/
  domain/
    usecases/
    entities/
```

## Folder Structure

```
shared/
  src/
    commonMain/
      kotlin/
        com/cryptx/cryptx/
          domain/
          repository/
          state/
          usecase/
          viewmodel/
androidApp/
  app/
    src/
      main/
        java/com/cryptx/cryptx/
        res/
iosApp/
  iosApp/
    Views/
    Components/
```

## Git Ignore

Created .gitignore to exclude build files, IDE files, and push only codebase.

## Build and Sync

- Sync project: `./gradlew sync`
- Build Android: `./gradlew assembleDebug`
- Build iOS: Open iosApp/iosApp.xcodeproj in Xcode and build

## Note

Project is not fully tested. Use with caution.