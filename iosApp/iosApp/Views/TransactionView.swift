import SwiftUI
import shared

struct TransactionView: View {
    @ObservedObject var navigationManager: NavigationManager
    @StateObject private var viewModel = SendTransactionViewModelWrapper()
    
    @State private var address = ""
    @State private var amount = ""
    @State private var selectedAsset = "Bitcoin"
    
    let assets = ["Bitcoin", "Ethereum", "Litecoin"]
    
    var body: some View {
        ZStack {
            PatternBackgroundView()
            
            VStack(spacing: 0) {
                // Header
                HStack {
                    Button(action: {
                        navigationManager.selectedTab = 0
                    }) {
                        Image(systemName: "chevron.left")
                            .font(.system(size: 20, weight: .semibold))
                            .foregroundColor(Color("OnBackground"))
                    }
                    
                    Spacer()
                    
                    Text("Send Transaction")
                        .font(.system(size: 18, weight: .bold))
                        .foregroundColor(Color("OnBackground"))
                    
                    Spacer()
                    
                    // Placeholder for symmetry
                    Color.clear
                        .frame(width: 24, height: 24)
                }
                .padding(.horizontal, 20)
                .padding(.top, 60)
                .padding(.bottom, 24)
                
                ScrollView {
                    VStack(spacing: 24) {
                        // Asset Selection
                        VStack(alignment: .leading, spacing: 12) {
                            Text("Select Asset")
                                .font(.system(size: 14, weight: .bold))
                                .foregroundColor(Color("OnBackground"))
                            
                            Menu {
                                ForEach(assets, id: \.self) { asset in
                                    Button(asset) {
                                        selectedAsset = asset
                                    }
                                }
                            } label: {
                                HStack {
                                    Text(selectedAsset)
                                        .foregroundColor(Color("OnSurface"))
                                    Spacer()
                                    Image(systemName: "chevron.down")
                                        .foregroundColor(Color("TextSecondary"))
                                }
                                .padding()
                                .background(Color("Surface"))
                                .cornerRadius(12)
                            }
                        }
                        
                        // Recipient Address
                        VStack(alignment: .leading, spacing: 12) {
                            Text("Recipient Address")
                                .font(.system(size: 14, weight: .bold))
                                .foregroundColor(Color("OnBackground"))
                            
                            TextField("Enter wallet address", text: $address)
                                .textFieldStyle(CustomTextFieldStyle())
                                .autocapitalization(.none)
                        }
                        
                        // Amount
                        VStack(alignment: .leading, spacing: 12) {
                            Text("Amount")
                                .font(.system(size: 14, weight: .bold))
                                .foregroundColor(Color("OnBackground"))
                            
                            TextField("0.00", text: $amount)
                                .textFieldStyle(CustomTextFieldStyle())
                                .keyboardType(.decimalPad)
                        }
                        
                        // Transaction Summary Card
                        VStack(spacing: 16) {
                            HStack {
                                Text("Transaction Summary")
                                    .font(.system(size: 16, weight: .bold))
                                    .foregroundColor(Color("OnSurface"))
                                Spacer()
                            }
                            
                            SummaryRow(title: "Network Fee", value: "0.0001 BTC")
                            SummaryRow(title: "Total Amount", value: "\(amount.isEmpty ? "0.00" : amount) \(selectedAsset)")
                        }
                        .padding(20)
                        .background(Color("Surface"))
                        .cornerRadius(16)
                        
                        // Send Button
                        Button(action: {
                            viewModel.sendTransaction(address: address, amount: amount)
                        }) {
                            Text("Send Transaction")
                                .font(.system(size: 16, weight: .semibold))
                                .foregroundColor(Color("OnPrimary"))
                                .frame(maxWidth: .infinity)
                                .padding(.vertical, 16)
                                .background(Color("Primary"))
                                .cornerRadius(12)
                        }
                        .disabled(address.isEmpty || amount.isEmpty)
                        .opacity(address.isEmpty || amount.isEmpty ? 0.5 : 1.0)
                        
                        Spacer(minLength: 40)
                    }
                    .padding(.horizontal, 20)
                }
            }
        }
        .ignoresSafeArea(edges: .top)
    }
}

struct SummaryRow: View {
    let title: String
    let value: String
    
    var body: some View {
        HStack {
            Text(title)
                .font(.system(size: 14))
                .foregroundColor(Color("TextSecondary"))
            Spacer()
            Text(value)
                .font(.system(size: 14, weight: .semibold))
                .foregroundColor(Color("OnSurface"))
        }
    }
}

#Preview {
    TransactionView(navigationManager: NavigationManager())
}
