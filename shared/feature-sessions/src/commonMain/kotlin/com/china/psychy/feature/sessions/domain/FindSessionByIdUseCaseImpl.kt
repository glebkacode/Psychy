package com.china.psychy.feature.sessions.domain

import com.china.psychy.feature.sessions.data.SessionRepository
import me.tatarka.inject.annotations.Inject

@Inject
class FindSessionByIdUseCaseImpl(
    private val sessionRepository: SessionRepository
) : FindSessionByIdUseCase {

    override suspend fun invoke(id: Long) = sessionRepository.findSessionById(id)
}