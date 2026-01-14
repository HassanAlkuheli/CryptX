import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

// ViewModel Wrappers for KMP shared code
class AuthViewModelWrapper {
    private val viewModel: AuthViewModel
    val authState = MutableStateFlow(AuthState(email = "", password = "", biometricEnabled = false))
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    init {
        val profileRepository = ProfileRepositoryImpl()
        val biometricRepository = IOSBiometricRepository()
        val signInUseCase = SignInUseCase(profileRepository, biometricRepository)
        this.viewModel = AuthViewModel(signInUseCase)

        // Observe state changes from KMP
        observeState()
    }

    fun signIn(email: String, password: String, useBiometric: Boolean) {
        viewModel.signIn(email, password, useBiometric)
    }

    private fun observeState() {
        // Collect state flow from KMP
        viewModel.state.onEach { state ->
            authState.value = state
        }.launchIn(scope)
    }
}

class WalletViewModelWrapper {
    private val viewModel: WalletViewModel
    val balance = MutableStateFlow("$12,345.67")
    val holdings = MutableStateFlow<List<HoldingData>>(emptyList())

    init {
        val getWalletUseCase = GetWalletUseCase()
        this.viewModel = WalletViewModel(getWalletUseCase)
    }

    fun loadWallet() {
        viewModel.loadWallet()

        // Mock data for now
        holdings.value = listOf(
            HoldingData(symbol = "BTC", name = "Bitcoin", amount = "$8,234.56", change = "+5.2%", changePositive = true),
            HoldingData(symbol = "ETH", name = "Ethereum", amount = "$2,891.34", change = "-2.1%", changePositive = false),
            HoldingData(symbol = "LTC", name = "Litecoin", amount = "$1,219.77", change = "+3.4%", changePositive = true)
        )
    }
}

class SendTransactionViewModelWrapper {
    private val viewModel: SendTransactionViewModel
    val transactionState = MutableStateFlow(SendState(status = "", message = ""))

    init {
        val validateAddressUseCase = ValidateAddressUseCase()
        val sendTransactionUseCase = SendTransactionUseCase()
        this.viewModel = SendTransactionViewModel(
            validateAddressUseCase,
            sendTransactionUseCase
        )
    }

    fun sendTransaction(address: String, amount: String) {
        viewModel.sendTransaction(address, amount)
    }
}

class ProfileViewModelWrapper {
    private val viewModel: ProfileViewModel
    val profileState = MutableStateFlow(ProfileState(profile = null))

    init {
        val profileRepository = ProfileRepositoryImpl()
        val biometricRepository = IOSBiometricRepository()
        val signInUseCase = SignInUseCase(profileRepository, biometricRepository)
        this.viewModel = ProfileViewModel(signInUseCase)
    }

    fun updateProfile(biometricEnabled: Boolean) {
        viewModel.updateProfile(biometricEnabled)
    }
}

// iOS Biometric Repository Implementation
class IOSBiometricRepository : BiometricRepository {
    override suspend fun promptBiometric(): String? {
        // Return null for now - actual implementation would use LAContext
        return null
    }
}
