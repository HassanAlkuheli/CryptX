import SwiftUI

struct PatternBackgroundView: View {
    var body: some View {
        ZStack {
            Color("Background")
                .ignoresSafeArea()
            
            // Grid pattern
            GeometryReader { geometry in
                let gridSize: CGFloat = 30
                let columns = Int(geometry.size.width / gridSize) + 1
                let rows = Int(geometry.size.height / gridSize) + 1
                
                ForEach(0..<rows, id: \.self) { row in
                    ForEach(0..<columns, id: \.self) { column in
                        Circle()
                            .fill(Color("OnBackground").opacity(0.03))
                            .frame(width: 4, height: 4)
                            .position(
                                x: CGFloat(column) * gridSize,
                                y: CGFloat(row) * gridSize
                            )
                    }
                }
            }
            
            // Gradient glows
            Circle()
                .fill(
                    RadialGradient(
                        gradient: Gradient(colors: [
                            Color("Primary").opacity(0.15),
                            Color.clear
                        ]),
                        center: .topLeading,
                        startRadius: 0,
                        endRadius: 300
                    )
                )
                .frame(width: 400, height: 400)
                .position(x: 100, y: 100)
                .blur(radius: 60)
            
            Circle()
                .fill(
                    RadialGradient(
                        gradient: Gradient(colors: [
                            Color("Primary").opacity(0.1),
                            Color.clear
                        ]),
                        center: .bottomTrailing,
                        startRadius: 0,
                        endRadius: 300
                    )
                )
                .frame(width: 400, height: 400)
                .position(x: UIScreen.main.bounds.width - 100, 
                         y: UIScreen.main.bounds.height - 100)
                .blur(radius: 60)
        }
    }
}

#Preview {
    PatternBackgroundView()
}
