//
//  SignInViewModel.swift
//  iosApp
//
//  Created by Калиниченко Глеб on 29.01.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

@available(iOS 17.0, *)
@Observable
class SignInViewModel {
    var email: String = ""
    var password: String = ""
    
    func emailChanged(text: String) {
        email = text
    }
    
    func passwordChanged(text: String) {
        password = text
    }
}
