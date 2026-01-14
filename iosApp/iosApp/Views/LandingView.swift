import SwiftUI

struct LandingView: View {
    @ObservedObject var navigationManager: NavigationManager
    
    var body: some View {
        ZStack {
            // Background with pattern
            PatternBackgroundView()
            
            VStack(spacing: 24) {
                Spacer()
                
                // Logo
                HStack(spacing: 0) {
                    Text("Crypt")
                        .font(.system(size: 48, weight: .bold))
                        .foregroundColor(Color("OnBackground"))
                    Text("X")
                        .font(.system(size: 48, weight: .bold))
                        .foregroundColor(Color("Primary"))
                }
                .padding(.top, 80)
                
                Spacer()
                
                // Illustration
                Image(systemName: "bitcoinsign.circle.fill")
                    .resizable()
                    .scaledToFit()
                    .frame(width: 200, height: 200)
                    .foregroundColor(Color("Primary"))
                    .padding(.trailing, 40)
                
                Spacer()
                
                // Hero Text
                VStack(spacing: 16) {
                    Text("Jump start your")
                        .font(.system(size: 32, weight: .semibold))
                        .foregroundColor(Color("OnBackground"))
                    
                    Text("crypto portfolio")
                        .font(.system(size: 32, weight: .bold))
                        .foregroundColor(Color("Primary"))
                    
                    Text("Take your investment portfolio to the next level")
                        .font(.system(size: 16))
                        .foregroundColor(Color("TextSecondary"))
                        .multilineTextAlignment(.center)
                        .padding(.horizontal, 32)
                }
                
                Spacer()
                
                // Get Started Button
                Button(action: {
                    navigationManager.navigate(to: .login)
                }) {
                    Text("Get Started")
                        .font(.system(size: 16, weight: .semibold))
                        .foregroundColor(Color("OnPrimary"))
                        .frame(maxWidth: .infinity)
                        .padding(.vertical, 16)
                        .background(Color("Primary"))
                        .cornerRadius(12)
                }
                .padding(.horizontal, 32)
                .padding(.bottom, 48)
            }
        }
        .ignoresSafeArea()
    }
}

#Preview {
    LandingView(navigationManager: NavigationManager())
}
