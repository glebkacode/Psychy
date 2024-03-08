package com.china.psychy.feature.auth.domain.loginuser

import com.china.psychy.feature.auth.model.User

typealias LoginUserUseCase = suspend (User) -> Unit