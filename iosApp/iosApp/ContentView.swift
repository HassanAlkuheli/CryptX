import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var navigationManager = NavigationManager()
    
    var body: some View {
        Group {
            switch navigationManager.currentScreen {
            case .landing:
                LandingView(navigationManager: navigationManager)
            case .login:
                LoginView(navigationManager: navigationManager)
            case .dashboard:
                MainTabView(navigationManager: navigationManager)
            }
        }
        .animation(.easeInOut, value: navigationManager.currentScreen)
    }
}

// Navigation Manager
class NavigationManager: ObservableObject {
    @Published var currentScreen: AppScreen = .landing
    @Published var selectedTab: Int = 0
    
    func navigate(to screen: AppScreen) {
        currentScreen = screen
    }
}

enum AppScreen {
    case landing
    case login
    case dashboard
}

#Preview {
    ContentView()
}
