import SwiftUI
import shared

struct DashboardView: View {
    @ObservedObject var navigationManager: NavigationManager
    @StateObject private var viewModel = WalletViewModelWrapper()
    
    var body: some View {
        ZStack {
            PatternBackgroundView()
            
            ScrollView {
                VStack(spacing: 24) {
                    // Header
                    HStack {
                        VStack(alignment: .leading, spacing: 4) {
                            Text("Hello, User")
                                .font(.system(size: 24, weight: .bold))
                                .foregroundColor(Color("OnBackground"))
                            Text("Welcome back!")
                                .font(.system(size: 14))
                                .foregroundColor(Color("TextSecondary"))
                        }
                        
                        Spacer()
                        
                        Button(action: {
                            navigationManager.selectedTab = 2
                        }) {
                            Circle()
                                .fill(Color("Primary"))
                                .frame(width: 40, height: 40)
                                .overlay(
                                    Text("U")
                                        .font(.system(size: 16, weight: .bold))
                                        .foregroundColor(Color("OnPrimary"))
                                )
                        }
                    }
                    .padding(.horizontal, 20)
                    .padding(.top, 60)
                    
                    // Wallet Card
                    WalletCardView(balance: viewModel.balance)
                        .padding(.horizontal, 20)
                    
                    // Action Buttons
                    HStack(spacing: 12) {
                        ActionButton(title: "Deposit", icon: "arrow.down.circle.fill") {
                            // Handle deposit
                        }
                        
                        ActionButton(title: "Withdraw", icon: "arrow.up.circle.fill", isPrimary: false) {
                            // Handle withdraw
                        }
                    }
                    .padding(.horizontal, 20)
                    
                    // My Holdings
                    VStack(alignment: .leading, spacing: 12) {
                        Text("My Holdings")
                            .font(.system(size: 14, weight: .bold))
                            .foregroundColor(Color("OnBackground"))
                            .padding(.horizontal, 20)
                        
                        VStack(spacing: 0) {
                            ForEach(viewModel.holdings, id: \.symbol) { holding in
                                AssetItemView(holding: holding)
                                
                                if holding.symbol != viewModel.holdings.last?.symbol {
                                    Divider()
                                        .padding(.horizontal, 20)
                                }
                            }
                        }
                        .background(Color("Surface"))
                        .cornerRadius(16)
                        .padding(.horizontal, 20)
                    }
                    
                    Spacer(minLength: 100)
                }
            }
        }
        .ignoresSafeArea(edges: .top)
        .onAppear {
            viewModel.loadWallet()
        }
    }
}

struct WalletCardView: View {
    let balance: String
    
    var body: some View {
        ZStack {
            // Dark card background with gradient
            RoundedRectangle(cornerRadius: 20)
                .fill(
                    LinearGradient(
                        gradient: Gradient(colors: [Color("Surface"), Color("Surface").opacity(0.8)]),
                        startPoint: .topLeading,
                        endPoint: .bottomTrailing
                    )
                )
            
            VStack(alignment: .leading, spacing: 16) {
                HStack {
                    Text("Total Balance")
                        .font(.system(size: 14))
                        .foregroundColor(Color("TextSecondary"))
                    
                    Spacer()
                    
                    Image(systemName: "bitcoinsign.circle.fill")
                        .font(.system(size: 24))
                        .foregroundColor(Color("Primary"))
                }
                
                Text(balance)
                    .font(.system(size: 36, weight: .bold))
                    .foregroundColor(Color("OnSurface"))
                
                HStack(spacing: 8) {
                    Image(systemName: "arrow.up.right")
                        .font(.system(size: 12))
                        .foregroundColor(.green)
                    Text("+12.5%")
                        .font(.system(size: 14, weight: .semibold))
                        .foregroundColor(.green)
                }
            }
            .padding(24)
        }
        .frame(height: 180)
    }
}

struct ActionButton: View {
    let title: String
    let icon: String
    var isPrimary: Bool = true
    let action: () -> Void
    
    var body: some View {
        Button(action: action) {
            HStack {
                Image(systemName: icon)
                Text(title)
            }
            .font(.system(size: 14, weight: .semibold))
            .foregroundColor(isPrimary ? Color("OnPrimary") : Color("OnBackground"))
            .frame(maxWidth: .infinity)
            .padding(.vertical, 14)
            .background(isPrimary ? Color("Primary") : Color("Surface"))
            .cornerRadius(12)
            .overlay(
                RoundedRectangle(cornerRadius: 12)
                    .stroke(isPrimary ? Color.clear : Color("OnBackground").opacity(0.3), lineWidth: 1)
            )
        }
    }
}

struct AssetItemView: View {
    let holding: HoldingData
    
    var body: some View {
        HStack(spacing: 12) {
            Circle()
                .fill(Color("Primary").opacity(0.2))
                .frame(width: 48, height: 48)
                .overlay(
                    Text(String(holding.symbol.prefix(1)))
                        .font(.system(size: 20, weight: .bold))
                        .foregroundColor(Color("Primary"))
                )
            
            VStack(alignment: .leading, spacing: 4) {
                Text(holding.name)
                    .font(.system(size: 14, weight: .semibold))
                    .foregroundColor(Color("OnSurface"))
                
                Text(holding.symbol)
                    .font(.system(size: 12))
                    .foregroundColor(Color("TextSecondary"))
            }
            
            Spacer()
            
            VStack(alignment: .trailing, spacing: 4) {
                Text(holding.amount)
                    .font(.system(size: 14, weight: .semibold))
                    .foregroundColor(Color("OnSurface"))
                
                HStack(spacing: 4) {
                    Image(systemName: holding.changePositive ? "arrow.up.right" : "arrow.down.right")
                        .font(.system(size: 10))
                    Text(holding.change)
                        .font(.system(size: 12))
                }
                .foregroundColor(holding.changePositive ? .green : .red)
            }
        }
        .padding(.horizontal, 20)
        .padding(.vertical, 12)
    }
}

struct HoldingData {
    let symbol: String
    let name: String
    let amount: String
    let change: String
    let changePositive: Bool
}

#Preview {
    DashboardView(navigationManager: NavigationManager())
}
