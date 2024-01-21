package com.china.psychy.auth.domain

import com.china.psychy.auth.models.User

typealias RegisterUserUseCase = suspend (User) -> Unit