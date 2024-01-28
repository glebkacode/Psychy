package com.china.psychy.feature.sessions.domain

import com.china.psychy.feature.sessions.model.Session

typealias FindSessionByIdUseCase = suspend (Long) -> Session