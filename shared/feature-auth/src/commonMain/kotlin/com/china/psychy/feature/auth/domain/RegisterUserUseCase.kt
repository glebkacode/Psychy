package com.china.psychy.feature.auth.domain

import com.china.psychy.feature.auth.model.User

typealias RegisterUserUseCase = suspend (User) -> Unit