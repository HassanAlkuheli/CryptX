# iOS App - CryptX Wallet

A native iOS application for the CryptX crypto wallet built with SwiftUI and Kotlin Multiplatform (KMP).

## Architecture

### Technology Stack
- **UI Framework**: SwiftUI (iOS 16+)
- **Language**: Swift 5.9+
- **Shared Logic**: Kotlin Multiplatform (KMP)
- **Authentication**: LocalAuthentication Framework (Face ID/Touch ID)
- **State Management**: Combine + ObservableObject

### Project Structure
```
iosApp/
├── iosApp/
│   ├── CryptoWalletApp.swift          # App entry point
│   ├── ContentView.swift              # Root view with navigation
│   ├── Info.plist                     # App configuration
│   │
│   ├── Views/                         # SwiftUI Views
│   │   ├── LandingView.swift          # Landing/welcome screen
│   │   ├── LoginView.swift            # Authentication screen
│   │   ├── MainTabView.swift          # Tab navigation
│   │   ├── DashboardView.swift        # Home/wallet overview
│   │   ├── TransactionView.swift      # Send crypto screen
│   │   └── ProfileView.swift          # User profile & settings
│   │
│   ├── ViewModels/                    # KMP wrappers
│   │   └── ViewModelWrappers.swift    # Swift wrappers for KMP ViewModels
│   │
│   ├── Components/                    # Reusable UI components
│   │   └── PatternBackgroundView.swift
│   │
│   └── Assets.xcassets/               # Colors, images, icons
│       ├── Primary.colorset
│       ├── Background.colorset
│       ├── Surface.colorset
│       └── ...
│
└── iosApp.xcodeproj/                  # Xcode project file
```

## Features

### Implemented Screens

1. **Landing Screen**
   - App branding and logo
   - Hero section with crypto illustration
   - "Get Started" CTA button

2. **Login Screen**
   - Email/password authentication
   - Sign up flow
   - Biometric authentication (Face ID/Touch ID)
   - Form validation

3. **Dashboard**
   - Total balance display
   - Wallet card with balance & growth
   - Deposit/Withdraw actions
   - Holdings list with crypto assets
   - Real-time price changes

4. **Transaction Screen**
   - Asset selection dropdown
   - Recipient address input
   - Amount input
   - Transaction summary
   - Network fee display
   - Send button with validation

5. **Profile Screen**
   - User info display
   - Wallet address with copy
   - Biometric toggle
   - Dark mode toggle
   - Push notifications toggle
   - Settings navigation
   - Sign out button

### KMP Integration

The app uses Kotlin Multiplatform shared code for:
- **Business Logic**: All ViewModels (AuthViewModel, WalletViewModel, etc.)
- **Use Cases**: SignInUseCase, SendTransactionUseCase, etc.
- **Repositories**: ProfileRepository, BiometricRepository
- **Domain Models**: Profile, Wallet, Transaction states

## Setup Instructions

### Prerequisites
- macOS with Xcode 15+
- iOS 16+ SDK
- Kotlin Multiplatform project built

### Building the App

1. **Build KMP Framework**
   ```bash
   cd crypto_wallet
   ./gradlew :shared:assembleXCFramework
   ```

2. **Open in Xcode**
   ```bash
   open iosApp/iosApp.xcodeproj
   ```

3. **Configure Signing**
   - Select your development team
   - Update bundle identifier if needed

4. **Run**
   - Select target device/simulator
   - Press Cmd+R to build and run

## Design System

### Colors
- **Primary**: #4557DC (Electric Blue)
- **Background**: #1A1A1A (Dark Gray)
- **Surface**: #414141 (Medium Gray)
- **OnBackground**: #EDEDED (Light Gray)
- **OnSurface**: #FFFFFF (White)
- **TextSecondary**: #999999 (Gray)

### Typography
- **Display**: System Bold 48pt
- **Headline**: System Bold 32pt
- **Title**: System Semibold 24pt
- **Body**: System Regular 14-16pt
- **Caption**: System Regular 12pt

### Components
- **Buttons**: 12pt corner radius, 16pt vertical padding
- **Cards**: 16-20pt corner radius, surface background
- **Text Fields**: 12pt corner radius, surface background
- **Pattern Background**: Grid dots + radial gradients

## Navigation Flow

```
Landing Screen
    ↓
Login Screen
    ↓
Main Tab View
    ├── Dashboard (Home)
    ├── Transaction
    └── Profile
         ↓
      Sign Out → Landing Screen
```

## Dependencies

### iOS Frameworks
- SwiftUI
- Combine
- LocalAuthentication
- Foundation

### KMP Shared Module
- Coroutines support
- State management
- Repository pattern
- Use case pattern

## Future Enhancements

- [ ] Actual wallet integration (Web3/blockchain)
- [ ] Real-time price updates
- [ ] Transaction history
- [ ] QR code scanning for addresses
- [ ] Push notifications
- [ ] Keychain secure storage
- [ ] Offline mode support
- [ ] Multi-language support
- [ ] Accessibility improvements
- [ ] Unit & UI tests

## Testing

Currently includes preview providers for all views. To add tests:

1. Create `iosAppTests` target
2. Add unit tests for ViewModels
3. Add UI tests for navigation flows
4. Test biometric authentication flows

## Notes

- Biometric authentication requires physical device or simulator with Face ID/Touch ID enabled
- All crypto operations are mocked for demonstration
- Network calls should be added to KMP shared code
- Color scheme supports dark mode by default
