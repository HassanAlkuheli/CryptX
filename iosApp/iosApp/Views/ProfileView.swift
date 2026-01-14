import SwiftUI
import shared
import LocalAuthentication

struct ProfileView: View {
    @ObservedObject var navigationManager: NavigationManager
    @StateObject private var viewModel = ProfileViewModelWrapper()
    
    @State private var isBiometricEnabled = false
    @State private var isDarkMode = true
    @State private var notificationsEnabled = true
    
    var body: some View {
        ZStack {
            PatternBackgroundView()
            
            ScrollView {
                VStack(spacing: 24) {
                    // Header
                    HStack {
                        Text("Profile")
                            .font(.system(size: 24, weight: .bold))
                            .foregroundColor(Color("OnBackground"))
                        
                        Spacer()
                    }
                    .padding(.horizontal, 20)
                    .padding(.top, 60)
                    
                    // Profile Info Card
                    HStack(spacing: 16) {
                        Circle()
                            .fill(Color("Primary"))
                            .frame(width: 48, height: 48)
                            .overlay(
                                Text("U")
                                    .font(.system(size: 20, weight: .bold))
                                    .foregroundColor(Color("OnPrimary"))
                            )
                        
                        VStack(alignment: .leading, spacing: 4) {
                            Text("User")
                                .font(.system(size: 16, weight: .semibold))
                                .foregroundColor(Color("OnSurface"))
                            
                            Text("user@email.com")
                                .font(.system(size: 14))
                                .foregroundColor(Color("TextSecondary"))
                        }
                        
                        Spacer()
                    }
                    .padding(16)
                    .background(Color("Surface"))
                    .cornerRadius(16)
                    .padding(.horizontal, 20)
                    
                    // Wallet Section
                    SectionView(title: "Wallet") {
                        VStack(spacing: 0) {
                            SettingsRowView(
                                icon: "wallet.pass.fill",
                                title: "Wallet Address",
                                subtitle: "1A2B3C4D5E6F7G8H9I0J"
                            ) {
                                // Copy to clipboard
                            }
                        }
                    }
                    
                    // Security Section
                    SectionView(title: "Security") {
                        VStack(spacing: 0) {
                            ToggleRowView(
                                icon: "faceid",
                                title: "Biometric Authentication",
                                subtitle: "Face ID / Touch ID",
                                isOn: $isBiometricEnabled
                            ) {
                                if isBiometricEnabled {
                                    authenticateWithBiometric()
                                }
                            }
                            
                            Divider()
                                .padding(.leading, 60)
                            
                            ToggleRowView(
                                icon: "moon.fill",
                                title: "Dark Mode",
                                subtitle: "Theme preference",
                                isOn: $isDarkMode
                            )
                        }
                    }
                    
                    // Notifications Section
                    SectionView(title: "Notifications") {
                        ToggleRowView(
                            icon: "bell.fill",
                            title: "Push Notifications",
                            subtitle: "Get notified about transactions",
                            isOn: $notificationsEnabled
                        )
                    }
                    
                    // Settings Section
                    SectionView(title: "Settings") {
                        VStack(spacing: 0) {
                            NavigationRowView(
                                icon: "gearshape.fill",
                                title: "Preferences"
                            ) {
                                // Navigate to preferences
                            }
                            
                            Divider()
                                .padding(.leading, 60)
                            
                            NavigationRowView(
                                icon: "info.circle.fill",
                                title: "About"
                            ) {
                                // Navigate to about
                            }
                        }
                    }
                    
                    // Sign Out Button
                    Button(action: {
                        navigationManager.navigate(to: .landing)
                    }) {
                        Text("Sign Out")
                            .font(.system(size: 14, weight: .semibold))
                            .foregroundColor(.red)
                            .frame(maxWidth: .infinity)
                            .padding(.vertical, 16)
                            .background(Color.red.opacity(0.1))
                            .cornerRadius(16)
                    }
                    .padding(.horizontal, 20)
                    .padding(.bottom, 32)
                }
            }
        }
        .ignoresSafeArea(edges: .top)
    }
    
    private func authenticateWithBiometric() {
        let context = LAContext()
        var error: NSError?
        
        guard context.canEvaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, error: &error) else {
            isBiometricEnabled = false
            return
        }
        
        context.evaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, 
                             localizedReason: "Enable biometric authentication") { success, authError in
            DispatchQueue.main.async {
                if !success {
                    isBiometricEnabled = false
                }
            }
        }
    }
}

struct SectionView<Content: View>: View {
    let title: String
    let content: Content
    
    init(title: String, @ViewBuilder content: () -> Content) {
        self.title = title
        self.content = content()
    }
    
    var body: some View {
        VStack(alignment: .leading, spacing: 12) {
            Text(title)
                .font(.system(size: 14, weight: .bold))
                .foregroundColor(Color("OnBackground"))
                .padding(.horizontal, 20)
            
            content
                .background(Color("Surface"))
                .cornerRadius(16)
                .padding(.horizontal, 20)
        }
    }
}

struct ToggleRowView: View {
    let icon: String
    let title: String
    let subtitle: String
    @Binding var isOn: Bool
    var onChange: (() -> Void)? = nil
    
    var body: some View {
        HStack(spacing: 16) {
            Image(systemName: icon)
                .font(.system(size: 20))
                .foregroundColor(Color("Primary"))
                .frame(width: 28)
            
            VStack(alignment: .leading, spacing: 4) {
                Text(title)
                    .font(.system(size: 14, weight: .semibold))
                    .foregroundColor(Color("OnSurface"))
                
                Text(subtitle)
                    .font(.system(size: 12))
                    .foregroundColor(Color("TextSecondary"))
            }
            
            Spacer()
            
            Toggle("", isOn: $isOn)
                .labelsHidden()
                .tint(Color("Primary"))
                .onChange(of: isOn) { _ in
                    onChange?()
                }
        }
        .padding(16)
    }
}

struct NavigationRowView: View {
    let icon: String
    let title: String
    let action: () -> Void
    
    var body: some View {
        Button(action: action) {
            HStack(spacing: 16) {
                Image(systemName: icon)
                    .font(.system(size: 20))
                    .foregroundColor(Color("Primary"))
                    .frame(width: 28)
                
                Text(title)
                    .font(.system(size: 14, weight: .semibold))
                    .foregroundColor(Color("OnSurface"))
                
                Spacer()
                
                Image(systemName: "chevron.right")
                    .font(.system(size: 14))
                    .foregroundColor(Color("TextSecondary"))
            }
            .padding(16)
        }
    }
}

struct SettingsRowView: View {
    let icon: String
    let title: String
    let subtitle: String
    let action: () -> Void
    
    var body: some View {
        Button(action: action) {
            HStack(spacing: 16) {
                Image(systemName: icon)
                    .font(.system(size: 20))
                    .foregroundColor(Color("Primary"))
                    .frame(width: 28)
                
                VStack(alignment: .leading, spacing: 4) {
                    Text(title)
                        .font(.system(size: 14, weight: .semibold))
                        .foregroundColor(Color("OnSurface"))
                    
                    Text(subtitle)
                        .font(.system(size: 12))
                        .foregroundColor(Color("TextSecondary"))
                }
                
                Spacer()
                
                Button(action: action) {
                    Text("Copy")
                        .font(.system(size: 12, weight: .semibold))
                        .foregroundColor(Color("Primary"))
                }
            }
            .padding(16)
        }
    }
}

#Preview {
    ProfileView(navigationManager: NavigationManager())
}
