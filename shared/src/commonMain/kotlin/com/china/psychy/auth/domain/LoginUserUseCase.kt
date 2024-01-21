package com.china.psychy.auth.domain

import com.china.psychy.auth.models.User

typealias LoginUserUseCase = suspend (User) -> Unit