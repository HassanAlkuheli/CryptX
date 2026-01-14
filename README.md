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

## Architectural Patterns

### MVVM (Model-View-ViewModel)
MVVM separates the UI (View) from the business logic (ViewModel) and data (Model). The ViewModel exposes state and commands to the View, and reacts to user inputs.

- **Model**: Represents data and business logic.
- **View**: UI components that display data and capture user interactions.
- **ViewModel**: Manages UI state and handles logic, acting as a bridge between View and Model.

Example:
```kotlin
class WalletViewModel : ViewModel() {
    private val _balance = MutableStateFlow(0.0)
    val balance: StateFlow<Double> = _balance.asStateFlow()
    
    fun loadBalance() {
        viewModelScope.launch {
            // Simulate loading balance
            _balance.value = 100.0
        }
    }
}
```

### MVI (Model-View-Intent)
MVI emphasizes unidirectional data flow with immutable state. User actions are represented as Intents that update the Model (state), which the View observes.

- **Model**: Immutable application state.
- **View**: Renders the state and emits Intents based on user actions.
- **Intent**: Represents user intentions or events.

Example:
```kotlin
sealed class WalletIntent {
    object LoadBalance : WalletIntent()
    data class UpdateBalance(val amount: Double) : WalletIntent()
}

data class WalletState(val balance: Double = 0.0, val isLoading: Boolean = false)

class WalletViewModel : ViewModel() {
    private val _state = MutableStateFlow(WalletState())
    val state: StateFlow<WalletState> = _state.asStateFlow()
    
    fun processIntent(intent: WalletIntent) {
        when (intent) {
            is WalletIntent.LoadBalance -> {
                _state.value = _state.value.copy(isLoading = true)
                // Load balance and update state
                _state.value = _state.value.copy(balance = 100.0, isLoading = false)
            }
            is WalletIntent.UpdateBalance -> {
                _state.value = _state.value.copy(balance = intent.amount)
            }
        }
    }
}
```

### Hybrid Architecture Pattern
Hybrid combines elements of MVVM and MVI. For instance, use MVVM for simple, data-driven screens and MVI for complex, event-driven flows to balance simplicity and predictability.

Example: In a wallet app, use MVVM for the balance display screen (simple state management) and MVI for transaction processing (handling multiple intents like send, receive, confirm).

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