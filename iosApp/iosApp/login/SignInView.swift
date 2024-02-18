//
//  SignInView.swift
//  iosApp
//
//  Created by Калиниченко Глеб on 29.01.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

@available(iOS 17.0, *)
struct SignInView: View {
    @State private var viewModel = SignInViewModel()
    
    var body: some View {
        ZStack {
            Color.green.edgesIgnoringSafeArea(.all)
            VStack(alignment: .center, spacing: 32) {
                TextField("Email", text: $viewModel.email)
                    .onChange(of: viewModel.email) { newValue in
                        viewModel.emailChanged(text: newValue)
                    }
                Spacer().frame(height: 32)
                TextField("Password", text: $viewModel.password)
                    .onChange(of: viewModel.password) { newValue in
                        viewModel.passwordChanged(text: newValue)
                    }
            }
        }
    }
}

#Preview {
    if #available(iOS 17.0, *) {
        SignInView()
    } else {
        // Fallback on earlier versions
    }
}
