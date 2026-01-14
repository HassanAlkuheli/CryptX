import SwiftUI
import shared
import Combine

// ViewModel Wrappers for KMP shared code
class AuthViewModelWrapper: ObservableObject {
    private let viewModel: AuthViewModel
    @Published var authState = AuthState(email: "", password: "", biometricEnabled: false)
    
    init() {
        let profileRepository = ProfileRepositoryImpl()
        let biometricRepository = IOSBiometricRepository()
        let signInUseCase = SignInUseCase(profileRepository: profileRepository, biometricRepository: biometricRepository)
        self.viewModel = AuthViewModel(signInUseCase: signInUseCase)
        
        // Observe state changes from KMP
        observeState()
    }
    
    func signIn(email: String, password: String, useBiometric: Bool) {
        viewModel.signIn(email: email, password: password, useBiometric: useBiometric)
    }
    
    private func observeState() {
        // Collect state flow from KMP
        viewModel.state.collect { [weak self] state in
            DispatchQueue.main.async {
                self?.authState = state as! AuthState
            }
        } completionHandler: { error in
            if let error = error {
                print("Error observing state: \(error)")
            }
        }
    }
}

class WalletViewModelWrapper: ObservableObject {
    private let viewModel: WalletViewModel
    @Published var balance = "$12,345.67"
    @Published var holdings: [HoldingData] = []
    
    init() {
        let getWalletUseCase = GetWalletUseCase()
        self.viewModel = WalletViewModel(getWalletUseCase: getWalletUseCase)
    }
    
    func loadWallet() {
        viewModel.loadWallet()
        
        // Mock data for now
        holdings = [
            HoldingData(symbol: "BTC", name: "Bitcoin", amount: "$8,234.56", change: "+5.2%", changePositive: true),
            HoldingData(symbol: "ETH", name: "Ethereum", amount: "$2,891.34", change: "-2.1%", changePositive: false),
            HoldingData(symbol: "LTC", name: "Litecoin", amount: "$1,219.77", change: "+3.4%", changePositive: true)
        ]
    }
}

class SendTransactionViewModelWrapper: ObservableObject {
    private let viewModel: SendTransactionViewModel
    @Published var transactionState = SendState(status: "", message: "")
    
    init() {
        let validateAddressUseCase = ValidateAddressUseCase()
        let sendTransactionUseCase = SendTransactionUseCase()
        self.viewModel = SendTransactionViewModel(
            validateAddressUseCase: validateAddressUseCase,
            sendTransactionUseCase: sendTransactionUseCase
        )
    }
    
    func sendTransaction(address: String, amount: String) {
        viewModel.sendTransaction(address: address, amount: amount)
    }
}

class ProfileViewModelWrapper: ObservableObject {
    private let viewModel: ProfileViewModel
    @Published var profileState = ProfileState(profile: nil)
    
    init() {
        let profileRepository = ProfileRepositoryImpl()
        let biometricRepository = IOSBiometricRepository()
        let signInUseCase = SignInUseCase(profileRepository: profileRepository, biometricRepository: biometricRepository)
        self.viewModel = ProfileViewModel(signInUseCase: signInUseCase)
    }
    
    func updateProfile(biometricEnabled: Bool) {
        viewModel.updateProfile(biometricEnabled: biometricEnabled)
    }
}

// iOS Biometric Repository Implementation
class IOSBiometricRepository: BiometricRepository {
    func promptBiometric() async throws -> String? {
        // Return nil for now - actual implementation would use LAContext
        return nil
    }
}
