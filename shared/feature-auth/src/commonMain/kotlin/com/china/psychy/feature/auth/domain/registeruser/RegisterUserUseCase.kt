package com.china.psychy.feature.auth.domain.registeruser

import com.china.psychy.feature.auth.model.User

typealias RegisterUserUseCase = suspend (User) -> Unit