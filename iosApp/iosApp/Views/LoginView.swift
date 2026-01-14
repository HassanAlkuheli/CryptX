import SwiftUI
import shared
import LocalAuthentication

struct LoginView: View {
    @ObservedObject var navigationManager: NavigationManager
    @StateObject private var viewModel = AuthViewModelWrapper()
    
    @State private var email = ""
    @State private var password = ""
    @State private var confirmPassword = ""
    @State private var isSignUp = false
    @State private var errorMessage = ""
    @State private var showError = false
    
    var body: some View {
        ZStack {
            PatternBackgroundView()
            
            VStack(spacing: 20) {
                Text(isSignUp ? "Sign Up" : "Login")
                    .font(.system(size: 32, weight: .bold))
                    .foregroundColor(Color("OnBackground"))
                    .padding(.top, 60)
                
                Spacer()
                
                VStack(spacing: 16) {
                    // Email Field
                    TextField("Email", text: $email)
                        .textFieldStyle(CustomTextFieldStyle())
                        .autocapitalization(.none)
                        .keyboardType(.emailAddress)
                    
                    // Password Field
                    SecureField("Password", text: $password)
                        .textFieldStyle(CustomTextFieldStyle())
                    
                    // Confirm Password (Sign Up only)
                    if isSignUp {
                        SecureField("Confirm Password", text: $confirmPassword)
                            .textFieldStyle(CustomTextFieldStyle())
                    }
                    
                    // Error Message
                    if showError {
                        Text(errorMessage)
                            .font(.system(size: 14))
                            .foregroundColor(.red)
                            .padding(.vertical, 8)
                    }
                    
                    // Login Button
                    Button(action: {
                        viewModel.signIn(email: email.trimmingCharacters(in: .whitespaces), 
                                       password: password, 
                                       useBiometric: false)
                    }) {
                        Text(isSignUp ? "Create Account" : "Login")
                            .font(.system(size: 16, weight: .semibold))
                            .foregroundColor(Color("OnPrimary"))
                            .frame(maxWidth: .infinity)
                            .padding(.vertical, 16)
                            .background(Color("Primary"))
                            .cornerRadius(12)
                    }
                    
                    // Biometric Button
                    Button(action: {
                        authenticateWithBiometric()
                    }) {
                        HStack {
                            Image(systemName: "faceid")
                            Text("Use Biometric")
                        }
                        .font(.system(size: 16, weight: .semibold))
                        .foregroundColor(Color("OnBackground"))
                        .frame(maxWidth: .infinity)
                        .padding(.vertical, 16)
                        .background(Color("Surface"))
                        .cornerRadius(12)
                        .overlay(
                            RoundedRectangle(cornerRadius: 12)
                                .stroke(Color("OnBackground").opacity(0.3), lineWidth: 1)
                        )
                    }
                    .disabled(email.isEmpty || password.isEmpty)
                    .opacity(email.isEmpty || password.isEmpty ? 0.5 : 1.0)
                    
                    // Toggle Sign Up / Login
                    Button(action: {
                        isSignUp.toggle()
                    }) {
                        Text(isSignUp ? "Have an account? Login" : "Create an account")
                            .font(.system(size: 14))
                            .foregroundColor(Color("Primary"))
                    }
                }
                .padding(.horizontal, 32)
                
                Spacer()
            }
        }
        .ignoresSafeArea()
        .onReceive(viewModel.$authState) { state in
            if !state.email.isEmpty {
                navigationManager.navigate(to: .dashboard)
            }
        }
    }
    
    private func authenticateWithBiometric() {
        let context = LAContext()
        var error: NSError?
        
        guard context.canEvaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, error: &error) else {
            errorMessage = "Biometric authentication is not available"
            showError = true
            return
        }
        
        context.evaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, 
                             localizedReason: "Authenticate to access your wallet") { success, authError in
            DispatchQueue.main.async {
                if success {
                    viewModel.signIn(email: email.trimmingCharacters(in: .whitespaces), 
                                   password: password, 
                                   useBiometric: true)
                } else {
                    errorMessage = "Biometric authentication failed"
                    showError = true
                }
            }
        }
    }
}

// Custom TextField Style
struct CustomTextFieldStyle: TextFieldStyle {
    func _body(configuration: TextField<Self._Label>) -> some View {
        configuration
            .padding()
            .background(Color("Surface"))
            .cornerRadius(12)
            .overlay(
                RoundedRectangle(cornerRadius: 12)
                    .stroke(Color("OnBackground").opacity(0.2), lineWidth: 1)
            )
    }
}

#Preview {
    LoginView(navigationManager: NavigationManager())
}
