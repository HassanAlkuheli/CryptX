import SwiftUI

struct MainTabView: View {
    @ObservedObject var navigationManager: NavigationManager
    
    var body: some View {
        TabView(selection: $navigationManager.selectedTab) {
            DashboardView(navigationManager: navigationManager)
                .tabItem {
                    Image(systemName: "house.fill")
                    Text("Home")
                }
                .tag(0)
            
            TransactionView(navigationManager: navigationManager)
                .tabItem {
                    Image(systemName: "arrow.left.arrow.right")
                    Text("Transaction")
                }
                .tag(1)
            
            ProfileView(navigationManager: navigationManager)
                .tabItem {
                    Image(systemName: "person.fill")
                    Text("Profile")
                }
                .tag(2)
        }
        .accentColor(Color("Primary"))
    }
}

#Preview {
    MainTabView(navigationManager: NavigationManager())
}
