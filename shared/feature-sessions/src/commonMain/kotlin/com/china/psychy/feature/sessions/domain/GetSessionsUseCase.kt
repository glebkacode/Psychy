package com.china.psychy.feature.sessions.domain

import com.china.psychy.feature.sessions.model.Session

typealias GetSessionsUseCase = suspend () -> List<Session>