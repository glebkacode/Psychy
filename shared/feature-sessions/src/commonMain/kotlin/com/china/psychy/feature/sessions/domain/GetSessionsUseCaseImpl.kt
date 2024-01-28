package com.china.psychy.feature.sessions.domain

import com.china.psychy.feature.sessions.data.SessionRepository
import me.tatarka.inject.annotations.Inject

@Inject
class GetSessionsUseCaseImpl(
    private val sessionRepository: SessionRepository
) : GetSessionsUseCase {
    override suspend fun invoke() = sessionRepository.getSessions()
}